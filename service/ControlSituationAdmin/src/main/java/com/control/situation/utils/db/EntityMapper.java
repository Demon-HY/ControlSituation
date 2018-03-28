package com.control.situation.utils.db;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据封装
 * 
 * @author inning
 * @DateTime 2015-7-20 上午11:36:01
 *
 */
@SuppressWarnings("all")
public class EntityMapper implements RowMapper, Serializable {

	private Class<?> entityClass;

	public EntityMapper(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Object obj = null;

		List<String> colName = new ArrayList<String>();
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 0; i < meta.getColumnCount(); i++) {
			colName.add(meta.getColumnName(i + 1).toUpperCase());
		}

		try {
			obj = entityClass.newInstance();
			Method[] method = entityClass.getMethods();
			for (Method m : method) {
				if (m.isAnnotationPresent(Column.class)) {
					Column col = m.getAnnotation(Column.class);
					if (colName.contains(col.name().toUpperCase())) {
						String setMethodName = m.getName()
								.replace("get", "set");
						Method setMethod = entityClass.getMethod(setMethodName,
								m.getReturnType());
						setObject(obj, rs, setMethod, col.name(),
								m.getReturnType());
					}
				}
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}
		return obj;
	}

	private static void setObject(Object obj, ResultSet rs, Method m,
			String colName, Class<?> c) throws Exception {
		try {
			if (rs.getObject(colName) == null)
				return;
			if (c.equals(String.class)) {
				m.invoke(obj, rs.getString(colName));
			} else if (c.equals(Long.class) || c.equals(long.class)) {
				m.invoke(obj, rs.getLong(colName));
			} else if (c.equals(Date.class) && rs.getTimestamp(colName) != null) {
				m.invoke(obj, new Date(rs.getTimestamp(colName).getTime()));
			} else if (c.equals(Double.class) || c.equals(double.class)) {
				m.invoke(obj, rs.getDouble(colName));
			} else if (c.equals(Float.class) || c.equals(float.class)) {
				m.invoke(obj, rs.getFloat(colName));
			} else if (c.equals(Short.class)) {
				m.invoke(obj, rs.getShort(colName));
			} else if (c.equals(Integer.class) || c.equals(int.class)) {
				m.invoke(obj, rs.getInt(colName));
			} else if (c.equals(BigDecimal.class)) {
				m.invoke(obj, rs.getBigDecimal(colName));
			} else {
				m.invoke(obj, rs.getObject(colName));
			}
		} catch (Exception e) {
		}
	}

}
