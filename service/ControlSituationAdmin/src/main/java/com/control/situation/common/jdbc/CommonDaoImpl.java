package com.control.situation.common.jdbc;

import com.alibaba.fastjson.JSONObject;
import com.control.situation.entity.UserInfo;
import com.control.situation.utils.ValidateUtils;
import com.control.situation.utils.conversion.MapUtils;
import com.control.situation.utils.db.TableFieldInfo;
import com.control.situation.utils.db.TableInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Demon-Coffee on 2018/3/15 0015.
 */
@Service
public class CommonDaoImpl<T> implements CommonDao<T> {

	/**
	 * debug = true, 打印要执行的SQL，不会执行
	 */
	public static Boolean debug = false;

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public T selectById(Object idObj, Class<T> entityClass) {
		try {
			String idName = getPrimyName(entityClass);
			if (idName == null || idName.length() < 1) return null;

			StringBuilder sql = getSelectFrom(entityClass);
			if (sql == null) return null;

			sql.append(" WHERE ").append(idName).append(" = ? ");
			if (debug) {
				System.out.println(sql.toString()); return null;
			}

			Object[] arg = {idObj};
			List list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(entityClass), arg);
			if (list == null || list.isEmpty()) {
				return null;
			}

			return (T) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T> selectByCriteria(CommonDao.Criteria criteria, Class<T> entityClass) {
		StringBuilder sql = getSelectFrom(entityClass);
		if (sql == null) return null;
		sql.append(criteria.getCriteriaSQL());
		if (debug) {
			System.out.println(sql.toString()); return null;
		}

		Object[] params = criteria.getParam().toArray(new Object[criteria.getParam().size()]);
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(entityClass), params);
	}

    @Override
    public T selectOneByCriteria(CommonDao.Criteria criteria, Class<T> entityClass) {
        StringBuilder sql = getSelectFrom(entityClass);
        if (sql == null) return null;
        sql.append(criteria.getCriteriaSQL());
        if (debug) {
            System.out.println(sql.toString()); return null;
        }

        Object[] params = criteria.getParam().toArray(new Object[criteria.getParam().size()]);
        List<T> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(entityClass), params);
        if (ValidateUtils.isEmpty(list) || list.size() != 1) {
            return null;
        }
        return list.get(0);
    }

	@Override
	public Long countByCriteria(CommonDao.Criteria criteria, Class<T> entityClass) {
		String sql = "SELECT COUNT(1) AS num FROM " + getTableName(entityClass) + criteria.getCriteriaSQL();
		if (debug) {
			System.out.println(sql); return null;
		}

		Map<String, Object> map = jdbcTemplate.queryForMap(sql, criteria.getParam().toArray());
		return (Long) map.get("num");
	}

	@Override
	public int removeById(Object idObj, Class<T> entityClass) {
		String sql = new StringBuilder()
				.append("DELETE FROM ")
				.append(getTableName(entityClass))
				.append(" WHERE ")
				.append(getPrimyName(entityClass))
				.append(" = ? ").toString();
		if (debug) {
			System.out.println(sql); return 1;
		}

		Object[] arg = {idObj};
		return getJdbcTemplate().update(sql, arg);
	}

	@Override
	public int insert(T entity) {
		Field[] fields = entity.getClass().getDeclaredFields();
		Map<String, Object> obj = MapUtils.objectToMap(entity);

		StringBuilder sql1 = new StringBuilder("INSERT INTO ")
				.append(getTableName(entity.getClass()))
				.append(" (");
		StringBuilder sql2 = new StringBuilder(" VALUES(");
		List<Object> args = new ArrayList<>();

		for (Field field : fields) {
			if (field.getAnnotation(Column.class) == null) continue;

			String key = field.getName();
			String name = field.getAnnotation(Column.class).name();
			Object arg = obj.get(key);
			if (ValidateUtils.isEmpty(arg)) continue;

			sql1.append(name).append(",");
			sql2.append("?,");
			args.add(arg);
		}

//		for (String key : obj.keySet()) {
//			Object arg = obj.get(key);
//			if (ValidateUtils.isEmpty(arg)) continue;
//
//			sql1.append(key).append(",");
//			sql2.append("?,");
//			args.add(arg);
//		}
		sql1.deleteCharAt(sql1.length() - 1);
		sql1.append(") ");
		sql2.deleteCharAt(sql2.length() - 1);
		sql2.append(") ");
		String sql = sql1.append(sql2).toString();
		if (debug) {
			System.out.println(sql); return 1;
		}

		return getJdbcTemplate().update(sql, args.toArray());
	}

	@Override
	public int update(T entity) {
		Map<String, Object> obj = MapUtils.objectToMap(entity);
		StringBuilder sql1 = new StringBuilder("UPDATE ")
				.append(getTableName(entity.getClass()))
				.append(" SET ");
		String pkName = getPrimyName(entity.getClass());
		StringBuilder sql2 = new StringBuilder(" WHERE " + pkName + " = ? ");
		List<Object> args = new ArrayList<>();
		for (String key : obj.keySet()) {
			if (key.equals(pkName)) continue;

			Object arg = obj.get(key);
			if (ValidateUtils.isEmpty(arg)) continue;

			sql1.append(key).append(" = ?,");
			args.add(arg);
		}

		sql1.deleteCharAt(sql1.length() - 1);
		args.add(obj.get(pkName));
		String sql = sql1.append(sql2).toString();
		if (debug) {
			System.out.println(sql);  return 1;
		}

		return getJdbcTemplate().update(sql, args.toArray());
	}

	@Override
	public CommonDao.Criteria createCriteria() {
		return new Criteria();
	}

	/**
	 * 查询条件的实现
	 */
	class Criteria implements CommonDao.Criteria {
		private boolean not; //是否标记了非
		private boolean begin; //是否正在拼接第一个条件
		private boolean or;//是否修改连接词为OR
		StringBuilder criteriaSQL; //从where开始的条件sql
		List<Object> param; //参数列表
		String limitStr; //限制条数

		Criteria() {
			criteriaSQL = new StringBuilder("");
			param = new LinkedList<>();
			not = false;
			or = false;
			begin = true;
			limitStr = "";
		}

		public Criteria not() {
			not = true;
			return this;
		}

		@Override
		public CommonDao.Criteria or() {
			or = true;
			return this;
		}

		private void link() {
			//判断是否是第一个条件
			// ，如果是就加WHERE不加连接词
			// ，不是就直接加连接词
			if (begin) {
				criteriaSQL.append(" WHERE ");
			} else {
				if (or) {
					criteriaSQL.append(" OR ");
				} else {
					criteriaSQL.append(" AND ");
				}
			}
			or = false;
		}

		public Criteria eq(String field, Object val) {
			link();
			if (not) {
				criteriaSQL.append(field)
						.append(" != ?");
			} else {
				criteriaSQL.append(field)
						.append(" = ?");
			}
			not = false;
			begin = false;
			param.add(val);
			return this;
		}

		public Criteria like(String field, Object val) {
			link();
			if (not) {
				criteriaSQL.append(field)
						.append(" NOT LIKE ?");
			} else {
				criteriaSQL.append(field)
						.append(" LIKE ?");
			}
			not = false;
			begin = false;
			param.add("%" + val + "%");
			return this;
		}

		public Criteria between(String field, Object val1, Object val2) {
			link();
			if (not) {
				criteriaSQL.append(field)
						.append(" NOT BETWEEN ? AND ? ");
			} else {
				criteriaSQL.append(field)
						.append(" BETWEEN ? AND ? ");
			}
			not = false;
			begin = false;
			param.add(val1);
			param.add(val2);
			return this;
		}

		@Override
		public CommonDao.Criteria limit(int start, int row) {
			limitStr = " limit " + start + "," + row;
			return this;
		}

		public List<Object> getParam() {
			return this.param;
		}

		public StringBuilder getCriteriaSQL() {
			return new StringBuilder(criteriaSQL.toString() + limitStr);
		}
	}

	/**
	 * 获取查询的字段信息
	 *
	 * @return select ... from table where
	 */
	private StringBuilder getSelectFrom(Class<T> entityClass) {
		String fieldNames = getFields(entityClass);
		if (fieldNames == null || fieldNames.length() < 1) return null;

		StringBuilder sql = new StringBuilder("SELECT " + fieldNames + " FROM ");
		sql.append(getTableName(entityClass)).append(" ");

		return sql;
	}

	@Override
	public String getTableName(Class<?> entityClass) {
		return entityClass.getAnnotation(Table.class).name();
	}

	@Override
	public String getPrimyName(Class<?> entityClass) {
		String idName;
		try {
			Field[] fields = entityClass.getDeclaredFields();
			for (Field f : fields) {
				if (f.isAnnotationPresent(Id.class)) {
					idName = f.getAnnotation(Column.class).name();
					return idName;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getFields(Class<?> entityClass) {
		try {
			Field[] field = entityClass.getDeclaredFields();
			StringBuilder fields = new StringBuilder();
			for (Field f : field) {
				if (f.getAnnotation(Column.class) == null) continue;
				fields.append(f.getAnnotation(Column.class).name()).append(",");
			}
			return fields.length() == 0 ? null : fields.substring(0, fields.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getColumnCommentByTableName(String tableName) throws Exception {
		JSONObject obj = new JSONObject();

		Connection conn = getJdbcTemplate().getDataSource().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(" SHOW FULL columns FROM " + tableName);
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

	@Override
	public List<TableFieldInfo> getFieldInfoByTableName(String tableName) throws Exception {
		List<TableFieldInfo> fieldInfos = new ArrayList<>();

		Connection conn = getJdbcTemplate().getDataSource().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(" SHOW FULL columns FROM " + tableName);
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

	@Override
	public TableInfo getTableInfoByTableName(String tableName) throws Exception {
		Connection conn = getJdbcTemplate().getDataSource().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		TableInfo tableInfo = new TableInfo();
		try {
			rs = stmt.executeQuery(" SHOW CREATE TABLE " + tableName);
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
		// 开启 DEBUG 模式，不执行SQL，只打印SQL语句
		CommonDaoImpl.debug = true;

		CommonDao<UserInfo> common = new CommonDaoImpl<>();
		UserInfo user = new UserInfo();

		// selectById
		common.selectById(20, UserInfo.class);

		// selectByCriteria
		CommonDao.Criteria criteria = common.createCriteria();
		criteria.between("id", 10, 20)
				.eq("account", "admin")
				.like("nick_name", "Admin")
				.limit(0, 10);
		common.selectByCriteria(criteria, UserInfo.class);

		// countByCriteria
		criteria = common.createCriteria();
		criteria.eq("id", 20)
				.eq("account", "admin");
		common.countByCriteria(criteria, UserInfo.class);

		// removeById
		common.removeById(1, UserInfo.class);

		// insert
		user.setPassword("sads");
		user.setAccount("admin");
		common.insert(user);

		// update
		common.update(user);
	}
}
