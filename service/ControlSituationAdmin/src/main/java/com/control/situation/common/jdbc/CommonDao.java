package com.control.situation.common.jdbc;

import com.alibaba.fastjson.JSONObject;
import com.control.situation.utils.db.TableFieldInfo;
import com.control.situation.utils.db.TableInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC 基础方法封装
 * Created by Demon-Coffee on 2018/3/15.
 */
public interface CommonDao<T> {

	JdbcTemplate getJdbcTemplate();

	/**
	 * 根据主键查询一条数据
	 * @return 将记录转换成的po类的实例
	 */
	T selectById(Object idObj, Class<T> entityClass);

	/**
	 * 根据查询条件查询记录
	 * List<Student> students = commonDao
	 * .selectByCriteria("m_student"
	 * , commonDao.createCriteria()
	 * .not().like("id", "2013")
	 * .between("age", 10, 20)
	 * .not().eq("gender", "F")
	 * , Student.class);
	 *
	 * @param criteria  查询条件
	 * @param entityClass      类型
	 * @return 将记录转换成的po类的实例的列表
	 */
	List<T> selectByCriteria(Criteria criteria, Class<T> entityClass);

	/**
	 * 查询记录数
	 *
	 * @param criteria  查询条件
	 * @return 记录数
	 */
	Long countByCriteria(Criteria criteria, Class<T> entityClass);

	/**
	 * 根据主键删除一条记录
	 *
	 * @param idObj        主键值
	 * @return 影响行数 0或1
	 */
	int removeById(Object idObj, Class<T> entityClass);

	/**
	 * 保存一个对象为一条数据库记录
	 * 如果对象主键不存在，则会新建
	 * 如果对象主键已经存在，则会更新
	 *
	 * @param entity    要保存的对象实体
	 * @return
	 */
	int insert(T entity);

	/**
	 * 保存一个对象为一条数据库记录
	 * 如果对象主键不存在，则会新建
	 * 如果对象主键已经存在，则会更新
	 *
	 * @param entity    要保存的对象实体
	 * @return 状态码 OK/ unique 重复错误
	 */
	int update(T entity);

	/**
	 * 获取表名
	 */
	String getTableName(Class<?> entityClass);

	/**
	 * 获取主键名
	 */
	String getPrimyName(Class<?> entityClass);

	/**
	 * 获取该表所有字段
	 * @param entityClass 表实体类
	 * @return "id,field1,field2,..."
	 */
	String getFields(Class<?> entityClass);

	/**
	 * 获得某表所有字段的注释
	 * @param tableName 表名
	 */
	JSONObject getColumnCommentByTableName(String tableName) throws Exception;

	/**
	 * 获得数据表字段信息
	 *
	 * @param tableName 表名
	 *  +-----------+----------------+-----------------+------+-----+---------+----------------+---------------------------------+--------------+
	 *  | Field     | Type           | Collation       | Null | Key | Default | Extra          | Privileges                      | Comment      |
	 *  +-----------+----------------+-----------------+------+-----+---------+----------------+---------------------------------+--------------+
	 *  | uid       | bigint(11)     | NULL            | NO   | PRI | NULL    | auto_increment | select,insert,update,references | 用户ID       |
	 *  | name      | varchar(20)    | utf8_general_ci | YES  |     | NULL    |                | select,insert,update,references | 用户名       |
	 */
	List<TableFieldInfo> getFieldInfoByTableName(String tableName) throws Exception;

	/**
	 * 获取数据库信息
	 * @param tableName 表名
	 */
	TableInfo getTableInfoByTableName(String tableName) throws Exception;

	/**
	 * 查询条件
	 */
	interface Criteria {
		/**
		 * 使接下来的条件取非
		 */
		Criteria not();

		/**
		 * 使与下一个条件的连接词变为or，默认为and
		 */
		Criteria or();

		/**
		 * 相等
		 *
		 * @param field 字段名
		 * @param val   值
		 */
		Criteria eq(String field, Object val);

		/**
		 * 字符串匹配
		 *
		 * @param field 字段名
		 * @param val   值
		 */
		Criteria like(String field, Object val);

		/**
		 * 取两个值之间的值
		 *
		 * @param field 字段名
		 * @param val1  值1
		 * @param val2  值2
		 */
		Criteria between(String field, Object val1, Object val2);

		/**
		 * 限制查询记录数
		 *
		 * @param start 开始位置
		 * @param row   记录数
		 */
		Criteria limit(int start, int row);

		/**
		 * 获取参数列表
		 *
		 * @return 参数列表
		 */
		List<Object> getParam();

		/**
		 * 获取拼接好的where条件sql语句
		 *
		 * @return sql
		 */
		StringBuilder getCriteriaSQL();
	}

	/**
	 * 让实现类自己实现建立条件的方法
	 *
	 * @return 查询条件实例
	 */
	Criteria createCriteria();
}
