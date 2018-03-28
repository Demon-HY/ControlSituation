package com.control.situation.utils.db;

import com.alibaba.fastjson.JSONObject;
import com.control.situation.utils.db.EntityMapper;
import com.control.situation.utils.db.MustUpdate;
import com.control.situation.utils.db.TableFieldInfo;
import com.control.situation.utils.db.TableInfo;
import com.control.situation.utils.exception.LogicalException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 所有数据库Dao层基类
 *
 * Created by yhe on 2017/7/30 0030.
 */
public abstract class CommonDao {

    private static final Logger log = LogManager.getLogger(com.control.situation.utils.db.CommonDao.class);

    public JdbcTemplate jdbcTemplate;

    public abstract JdbcTemplate getJdbcTemplate();

    /**
     * 插入数据
     * @param obj
     * @return
     */
    public int insert(Object obj) {
        Class<?> entityClass = obj.getClass();

        List<String> colNamelist = new ArrayList<>();
        List<Object> objList = new ArrayList<>();
        try {
            Method[] method = entityClass.getDeclaredMethods();
            for (Method m : method) {
                // 跳过 set 方法
                if (m.getAnnotation(Column.class) == null) continue;

                // 判断insertable属性
                if (!m.getAnnotation(Column.class).insertable()) continue;

                Object o = m.invoke(obj);
                if (o == null) {
                    continue;
                }
                // 如果为主键，则不需要设置值
                if (m.isAnnotationPresent(Id.class)) {
                    continue;
                } else {
                    colNamelist.add("`"+m.getAnnotation(Column.class).name()+"`");
                    objList.add(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        StringBuffer sql = new StringBuffer("insert into ");
        sql.append(entityClass.getAnnotation(Table.class).name());
        sql.append(" (");
        sql.append(colNamelist.toString().replace("[", "").replace("]", ""));
        sql.append(") values ");
        sql.append(getQuestionStr(colNamelist.size()));

        if (log.isDebugEnabled()) {
            log.debug(sql);
            log.debug(objList);
        }
        return getJdbcTemplate().update(sql.toString(), objList.toArray());
    }

    private String getQuestionStr(int size) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < size; i++) {
            b.append(",?");
        }
        return b.toString().replaceFirst(",", "(") + ")";
    }

    /**
     * 获取查询字段
     * @param entityClass
     * @return
     */
    public String getFields(Class<?> entityClass) {
        try {
            Method[] method = entityClass.getDeclaredMethods();
            StringBuilder fields = new StringBuilder();
            for (Method m : method) {
                // 跳过 set 方法以及没有设置 Column 注解的 get 方法
                if (m.getAnnotation(Column.class) == null) continue;
                if (m.getAnnotation(Null.class) != null) continue;
                fields.append(m.getAnnotation(Column.class).name()).append(",");
            }
            return fields.length() == 0 ? null : fields.substring(0, fields.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 更新数据
     * @param obj
     * @return
     */
    public int update(Object obj) {
        Class<?> entityClass = obj.getClass();

        List<String> colNamelist = new ArrayList<>();
        List<Object> objList = new ArrayList<>();
        List<String> idNameList = new ArrayList<>();
        List<Object> idObjList = new ArrayList<>();
        try {
            Method[] method = entityClass.getDeclaredMethods();
            for (Method m : method) {
                if (m.isAnnotationPresent(Column.class)) {
                    // 跳过 set 方法
                    if (m.getAnnotation(Column.class) == null) continue;

                    // 判断updatable属性
                    if (!m.getAnnotation(Column.class).updatable()) continue;

                    Column colomn = m.getAnnotation(Column.class);
                    Object o = m.invoke(obj);
                    if (m.isAnnotationPresent(Id.class)) {
                        idNameList.add("`"+colomn.name()+"`=?");
                        idObjList.add(o);
                    } else {
                        if (o != null) {
                            colNamelist.add("`"+colomn.name()+"`=?");
                            objList.add(o);
                        }else{
                            if(m.isAnnotationPresent(MustUpdate.class)){
                                colNamelist.add("`"+colomn.name()+"`=?");
                                objList.add(o);
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        objList.addAll(idObjList);

        StringBuffer sql = new StringBuffer(" update ");
        sql.append(entityClass.getAnnotation(Table.class).name());
        sql.append(" set ").append(colNamelist.toString().replace("[", "").replace("]", ""));
        sql.append(" where ").append(idNameList.toString().replace("[", "").replace("]",
                "").replaceAll(",", " and "));

        if (log.isDebugEnabled()) {
            log.debug(sql);
            log.debug(objList);
        }
        return getJdbcTemplate().update(sql.toString(), objList.toArray());
    }

    /**
     * 删除单个实体
     * @param entityClass
     * @param idObj
     * @return
     */
    public int delete(Class<?> entityClass, Object idObj) {
        String idName = "";
        try {
            Method[] method = entityClass.getDeclaredMethods();
            for (Method m : method) {
                if (m.isAnnotationPresent(Id.class)) {
                    idName = m.getAnnotation(Column.class).name();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(entityClass.getAnnotation(Table.class).name());
        sql.append(" where ").append(idName).append("=?");

        if (log.isDebugEnabled()) {
            log.debug(sql);
            log.debug(idObj);
        }

        Object[] arg = { idObj };
        return getJdbcTemplate().update(sql.toString(), arg);
    }

    /**
     * 批量删除
     * @param entityClass
     * @param idObj
     * @return
     */
    public int batchDelete(Class<?> entityClass, Object[] idObj) {
        if (idObj == null || idObj.length < 1) {
            return -1;
        }

        // 主键名称
        String idName = "";
        try {
            Method[] method = entityClass.getDeclaredMethods();
            for (Method m : method) {
                if (m.isAnnotationPresent(Id.class)) {
                    idName = m.getAnnotation(Column.class).name();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuffer idsSb = new StringBuffer();
        String ids = Arrays.toString(idObj).replace("[", "").replace("]", "");

        for (Object anIdObj : idObj) {
            idsSb.append(anIdObj).append(",");
        }
        if (idsSb.indexOf(",") != -1) {
            idsSb.deleteCharAt(idsSb.lastIndexOf(","));
        }

        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(entityClass.getAnnotation(Table.class).name())
                .append(" where ")
                .append(idName)
                .append(" in (")
                .append(ids)
                .append(")");// in子集不能超过1000

        if (log.isDebugEnabled()) {
            log.debug(sql);
            log.debug(idsSb);
        }
        return getJdbcTemplate().update(sql.toString());
    }

    public List<?> findAll(Class<?> entityClass) {
        String tableName = entityClass.getAnnotation(Table.class).name();
        String sql = " select " + this.getFields(entityClass) + " from " + tableName + " ";

        return getJdbcTemplate().query(sql, new EntityMapper(entityClass));
    }

    public long count(Class<?> entityClass) throws LogicalException {
        String primaryIdName = getPrimaryIdName(entityClass);

        String tableName = entityClass.getAnnotation(Table.class).name();
        String sql = String.format(" select count(%s) as cut from %s ", primaryIdName, tableName);
        List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
        if (list == null || list.size() == 0) {
            return -1;
        }
        return Long.parseLong(list.get(0).get("cut").toString());
    }

    public String getPrimaryIdName(Class<?> entityClass) throws LogicalException {
        Method[] method = entityClass.getDeclaredMethods();
        for (Method m : method) {
            // 跳过 set 方法以及没有设置 Column 注解的 get 方法
            if (m.getAnnotation(Column.class) == null) continue;

            if (m.isAnnotationPresent(Id.class)) {
                return m.getAnnotation(Column.class).name();
            }
        }

        throw new LogicalException("ERR_PRIMARY_ID_NOT_FOUND", "entity class primary id not found.");
    }

    /**
     * 查询分页数据
     * @param pageIndex 页码
     * @param pageSize 每页数据
     * @return
     */
    public List<?> findPageList(long pageIndex, long pageSize, Class<?> entityClass) {
        return findPageList(pageIndex, pageSize, null, null, null, entityClass);
    }

    /**
     * 查询分页数据
     * @param pageIndex
     * @param pageSize
     * @param sort
     * @param order
     * @param entityClass
     * @return
     */
    public List<?> findPageList(long pageIndex, long pageSize, String sort, String order
            , Class<?> entityClass) {
        return findPageList(pageIndex, pageSize, sort, order, null, entityClass);
    }

    /**
     * 查询分页数据
     * @param pageIndex 页码
     * @param pageSize 每页数据
     * @param args 可选的查询参数
     * @return
     */
    public List<?> findPageList(long pageIndex, long pageSize, String sort, String order, Map<String, Object> args,
                                Class<?> entityClass) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select ")
                .append(getFields(entityClass))
                .append(" from ")
                .append(entityClass.getAnnotation(Table.class).name())
                .append(" where 1=1 ");

        List<Object> params = new ArrayList<>();
        sql.append(appendParams(args, params));

        if (sort != null && sort.length() > 1) {
            sql.append(" order by ")
                    .append(sort)
                    .append(" ")
                    .append(order)
                    .append(" ");
        }
        sql.append(" limit ?,? ");
        params.add(pageIndex);
        params.add(pageSize);
        return getJdbcTemplate().query(sql.toString(),
                new EntityMapper(entityClass), params.toArray());
    }

    /**
     * 查询数据条数
     * @param sort
     * @param order
     * @param entityClass
     * @return
     */
    public Long getTotalCount(String sort, String order,
                              Class<?> entityClass) {
        return getTotalCount( sort, order, null, entityClass);
    }

    /**
     * 查询数据条数
     * @param sort
     * @param order
     * @param args
     * @param entityClass
     * @return
     */
    public Long getTotalCount(String sort, String order, Map<String, Object> args,
                              Class<?> entityClass) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select ")
                .append(getFields(entityClass))
                .append(" from ")
                .append(entityClass.getAnnotation(Table.class).name())
                .append(" where 1=1 ");

        List<Object> params = new ArrayList<>();
        sql.append(appendParams(args, params));

        if (sort != null && sort.length() > 1) {
            sql.append(" order by ")
                    .append(sort)
                    .append(" ")
                    .append(order)
                    .append(", updatetime desc ");
        }

        return (long) getJdbcTemplate().queryForList(sql.toString(), params.toArray()).size();
    }

    /**
     * 查询 MD5
     * @return
     */
    public List<?> findByMD5(String md5, Class<?> entityClass) {
        String sql = " select " + getFields(entityClass) + " from " + entityClass.getAnnotation(Table.class).name()
                + " where status != 9 and md5=? ";
        List<Object> params = new ArrayList<>();
        params.add(md5);

        return getJdbcTemplate().query(sql, new EntityMapper(entityClass), params.toArray());
    }

    public List<?> findByMD52(String md5, Class<?> entityClass) {
        String sql = " select " + getFields(entityClass) + " from " + entityClass.getAnnotation(Table.class).name()
                + " where md5=? order by id desc";
        List<Object> params = new ArrayList<>();
        params.add(md5);

        return getJdbcTemplate().query(sql, new EntityMapper(entityClass), params.toArray());
    }

    /**
     * 查询 MD5
     * @return
     */
    public List<?> findByMD5Title(String md5, String title, Class<?> entityClass) {

        String sql = " select " + getFields(entityClass) + " from " + entityClass.getAnnotation(Table.class).name()
                + " where ";

        List<Object> params = new ArrayList<>();
        if (title != null && md5 != null) {
            sql += " file_name=? and md5=? ";
            params.add(title);
            params.add(md5);
        }
        else if (md5 != null) {
            sql += " md5=? ";
            params.add(md5);
        }
        else if (title != null) {
            sql += " file_name=? ";
            params.add(title);
        }

        sql += " order by id desc limit 0,1 ";

        return getJdbcTemplate().query(sql, new EntityMapper(entityClass), params.toArray());
    }

    /**
     * 生成 where 条件，string类型为模糊查询，其他都是精确查询
     * @param args
     * @param params
     * @return
     */
    private StringBuilder appendParams(Map<String, Object> args, List<Object> params) {
        StringBuilder _params = new StringBuilder(" ");
        if (args != null && args.size() > 0) {
            for (String key : args.keySet()) {
                Object obj = args.get(key);
                if (obj instanceof String) {
                    _params.append(" and ").append(key).append(" like '%").append(args.get(key)).append("%' ");
                } else  {
                    _params.append(" and ").append(key).append(" = ? ");
                    params.add(obj);
                }
            }
        }
        return _params;
    }

    private String getPrimyName(Class<?> entityClass) {
        String idName = "";
        try {
            Method[] method = entityClass.getDeclaredMethods();
            for (Method m : method) {
                if (m.isAnnotationPresent(Id.class)) {
                    idName = m.getAnnotation(Column.class).name();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idName;
    }

    /**
     * 查询单个实体
     * @param entityClass
     * @param idObj
     * @param <T>
     * @return
     */
    public <T> T find(Class<T> entityClass, Object idObj) {
        try {
            String idName = getPrimyName(entityClass);
            if (idName == null || idName.length() < 1) return null;
            String fieldNames = getFields(entityClass);
            if (fieldNames == null || fieldNames.length() < 1) return null;


            StringBuffer sql = new StringBuffer("select " + fieldNames + " from ");
            sql.append(entityClass.getAnnotation(Table.class).name());
            sql.append(" where ").append(idName).append("=?");

            if (log.isDebugEnabled()) {
                log.debug(sql);
                log.debug(idObj);
            }

            Object[] arg = { idObj };
            List list = getJdbcTemplate().query(sql.toString(), new EntityMapper(entityClass), arg);
            if (list == null || list.isEmpty()) {
                return null;
            }

            return (T) list.get(0);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量更新单个字段
     * @param id
     * @param fieldName 字段名
     * @param value 字段值
     * @param entityClass 实体类对象
     */
    public int updateSimpleField(Object id, String fieldName, Object value,
                                 Class<?> entityClass) {
        String primyName = getPrimyFieldName(entityClass);
        if (primyName == null || id == null) return -1;

        String sql = " update " +
                entityClass.getAnnotation(Table.class).name() +
                " set " + fieldName + " = ? " + " where " + primyName + " =? ";

        List<Object> params = new ArrayList<>();
        params.add(value);
        params.add(id);

        return getJdbcTemplate().update(sql, params.toArray());
    }

    /**
     * 批量更新单个字段
     * @param ids
     * @param fieldName 字段名
     * @param value 字段值
     * @param entityClass 实体类对象
     */
    public void batchUpdateSimpleField(String[] ids, String fieldName, Object value,
                                       Class<?> entityClass) {
        StringBuilder sql = new StringBuilder();
        sql.append(" update ")
                .append(entityClass.getAnnotation(Table.class).name())
                .append(" set ")
                .append(fieldName)
                .append(" = ? ")
                .append(" where ");

        List<Object> params = new ArrayList<>();
        params.add(value);
        sql.append(appendIds(ids, params));

        getJdbcTemplate().update(sql.toString(), params.toArray());
    }

    private StringBuilder appendIds(String[] ids, List<Object> params) {
        StringBuilder _params = new StringBuilder("");
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                if (id == null || id.length() < 1) {
                    continue;
                }
                if (_params.length() < 1) {
                    _params.append(" id=? ");
                    params.add(id);
                    continue;
                }
                _params.append(" or id=? ");
                params.add(id);
            }
        }
        return _params;
    }

    /**
     * 获取主键字段名
     * @param entityClass
     * @return
     */
    public String getPrimyFieldName(Class<?> entityClass) {
        try {
            Method[] method = entityClass.getDeclaredMethods();
            for (Method m : method) {
                if (m.isAnnotationPresent(Id.class)) {
                    return m.getAnnotation(Column.class).name();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获得某表所有字段的注释
     * @param tableName 表名
     */
    public JSONObject getColumnCommentByTableName(String tableName) throws Exception {
        JSONObject obj = new JSONObject();

        Connection conn = getJdbcTemplate().getDataSource().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                obj.put(rs.getString("Field"), rs.getString("Comment"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
        return obj;
    }

    /**
     * 获得数据表字段信息
     * @param tableName 表名
     *  +-----------+----------------+-----------------+------+-----+---------+----------------+---------------------------------+--------------+
     *  | Field     | Type           | Collation       | Null | Key | Default | Extra          | Privileges                      | Comment      |
     *  +-----------+----------------+-----------------+------+-----+---------+----------------+---------------------------------+--------------+
     *  | uid       | bigint(11)     | NULL            | NO   | PRI | NULL    | auto_increment | select,insert,update,references | 用户ID       |
     *  | name      | varchar(20)    | utf8_general_ci | YES  |     | NULL    |                | select,insert,update,references | 用户名       |
     */
    public List<TableFieldInfo> getFieldInfoByTableName(String tableName) throws Exception {
        List<TableFieldInfo> fieldInfos = new ArrayList<>();

        Connection conn = getJdbcTemplate().getDataSource().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("show full columns from " + tableName);
            TableFieldInfo fieldInfo = new TableFieldInfo();
            while (rs.next()) {
                fieldInfo.setFieldName(rs.getString("Field"));
                fieldInfo.setFieldComment(rs.getString("Comment"));
                fieldInfo.setFieldType(rs.getString("Type"));
                String key = rs.getString("Key");
                if (key != null && key.equals("PRI")) {
                    fieldInfo.setPrimary(true);
                } else {
                    fieldInfo.setPrimary(false);
                }
                fieldInfo.setNull(rs.getString("Null").equals("YES"));

                fieldInfos.add(fieldInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
        return fieldInfos;
    }

    /**
     * 获取数据库信息
     * @param tableName 表名
     */
    private TableInfo getTableInfoByTableName(String tableName) throws Exception {
        Connection conn = getJdbcTemplate().getDataSource().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        TableInfo tableInfo = new TableInfo();
        try {
            rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
            if (rs != null && rs.next()) {
                String createDDL = rs.getString(2);
                String comment = parse(createDDL);

                tableInfo.setTableName(tableName);
                tableInfo.setTableComment(comment);
                tableInfo.setListFieldInfos(getFieldInfoByTableName(tableName));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
        return tableInfo;
    }

    /**
     * 返回注释信息
     * @param all 数据库获取的字段描述， e.g:`update_time` datetime NOT NULL COMMENT '更新时间'
     */
    private static String parse(String all) {
        String comment;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }

    public static void main(String[] args) {
//        BackendFileInfo files = new BackendFileInfo();
//        files.setStatus(9);
//        files.setMd5("bb");
//        files.setId(111L);

//        CommonDao commonDao = new CommonDao();
//        commonDao.insert(files);
//        commonDao.update(files);
//        commonDao.delete(backend_files.class, 100L, null);
//        commonDao.batchDelete(backend_files.class, new Integer[]{1, 2, 3}, null);
//        System.out.println(commonDao.getFields(backend_files.class));
    }

}
