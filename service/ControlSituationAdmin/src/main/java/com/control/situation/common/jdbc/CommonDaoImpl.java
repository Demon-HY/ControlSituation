package com.control.situation.common.jdbc;

import com.demon.utils.db.EntityMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Created by demon-Coffee on 2018/3/15 0015.
 */
@Service
public class CommonDaoImpl<T> implements CommonDao<T> {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public T selectById(Object idObj, Class<T> entityClass) {
		try {
			String idName = getPrimyName(entityClass);
			if (idName == null || idName.length() < 1) return null;

			StringBuilder sql = getSelectFrom(entityClass);
			if (sql == null) return null;

			sql.append(idName).append(" = ? ");

			Object[] arg = { idObj };
			List list = getJdbcTemplate().query(sql.toString(), new EntityMapper(entityClass), arg);
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
	public List<T> selectByCriteria(String tableName, CommonDao.Criteria criteria, Class<T> entityClass) {
		StringBuilder sql = getSelectFrom(entityClass);
		if (sql == null) return null;

		return null;
	}

	@Override
	public long countByCriteria(String tableName, CommonDao.Criteria criteria) {
		return 0;
	}

	@Override
	public int removeById(String tableName, String pkName, String id) {
		return 0;
	}

	@Override
	public Long insert(String tableName, String pkName, T entity) {
		return null;
	}

	@Override
	public String update(String tableName, String pkName, T entity) {
		return null;
	}

	@Override
	public CommonDao.Criteria createCriteria() {
		return new Criteria();
	}

	/**
	 * 查询条件的实现
	 */
	class Criteria implements CommonDao.Criteria{
		private boolean not; //是否标记了非
		private boolean begin; //是否正在拼接第一个条件
		private boolean or;//是否修改连接词为OR
		StringBuilder criteriaSQL; //从where开始的条件sql
		List<Object> param; //参数列表
		String limitStr; //限制条数

		Criteria(){
			criteriaSQL = new StringBuilder("");
			param = new LinkedList<>();
			not = false;
			begin = true;
			limitStr = "";
		}

		public Criteria not(){
			not = true;
			return this;
		}

		@Override
		public CommonDao.Criteria or() {
			or = true;
			return this;
		}

		private void link(){
			//判断是否是第一个条件
			// ，如果是就加WHERE不加连接词
			// ，不是就直接加连接词
			if(begin){
				criteriaSQL.append(" WHERE ");
			}else{
				if(or){
					criteriaSQL.append(" OR ");
				}else{
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

		public Criteria like(String field, Object val){
			link();
			if(not){
				criteriaSQL.append(field)
						.append(" NOT LIKE ?");
			}else{
				criteriaSQL.append(field)
						.append(" LIKE ?");
			}
			not = false;
			begin = false;
			param.add("%"+val+"%");
			return this;
		}
		public Criteria between(String field, Object val1, Object val2){
			link();
			if(not){
				criteriaSQL.append(field)
						.append(" NOT BETWEEN ? AND ? ");
			}else{
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

		public List<Object> getParam(){
			return this.param;
		}
		public StringBuilder getCriteriaSQL(){
			return new StringBuilder(criteriaSQL.toString()+limitStr);
		}
	}

	/**
	 * 获取查询的字段信息
	 * @return select ... from table where
	 */
	private StringBuilder getSelectFrom(Class<T> entityClass) {
		String fieldNames = getFields(entityClass);
		if (fieldNames == null || fieldNames.length() < 1) return null;

		StringBuilder sql = new StringBuilder("SELECT " + fieldNames + " FROM ");
		sql.append(entityClass.getAnnotation(Table.class).name()).append(" WHERE ");

		return sql;
	}

	/**
	 * 获取主键名
	 */
	private String getPrimyName(Class<?> entityClass) {
		String idName;
		try {
			Method[] method = entityClass.getDeclaredMethods();
			for (Method m : method) {
				if (m.isAnnotationPresent(Id.class)) {
					idName = m.getAnnotation(Column.class).name();
					return idName;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getFields(Class<?> entityClass) {
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
}
