package com.control.situation.utils.excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.control.situation.utils.ValidateUtils;
import com.control.situation.utils.excel.ExcelAnnotation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 利用了JAVA的反射机制解析 Excel
 * <p>
 * 需要在要解析的实体类字段上添加 @ExcelAnnotation 注解
 * <p>
 * Created by Demon on 2017/7/22 0022.
 */
public class ExcelUtils {

	private static Logger logger = LogManager.getLogger(com.control.situation.utils.excel.ExcelUtils.class);
	private Workbook wb;
	private Sheet sheet;
	private Row row;

	public ExcelUtils(String fileType, InputStream is) {
		try {
			if ("xls".equals(fileType)) {
				wb = new HSSFWorkbook(is);
			} else if ("xlsx".equals(fileType)) {
				wb = new XSSFWorkbook(is);
			} else {
				wb = null;
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
	}

	/**
	 * 读取Excel表格表头的内容
	 *
	 * @return String 表头内容的数组
	 */
	public Iterator<Cell> readExcelTitle() throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);

		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		logger.info("colNum:" + colNum);

		return row.cellIterator();
	}

	/**
	 * 读取Excel数据内容
	 *
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, Map<Integer, Object>> readExcelContentForMap()
			throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}
		Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();


		sheet = wb.getSheetAt(0);

		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();

		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				cellValue.put(j, obj);
				j++;
			}
			content.put(i, cellValue);
		}
		return content;
	}

	/**
	 * 读取Excel数据内容
	 *
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public JSONArray readExcelContentForJSON() throws Exception {
		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}

		JSONArray content = new JSONArray();

		sheet = wb.getSheetAt(0);

		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();

		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			JSONObject rows = new JSONObject();
			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				rows.put(String.valueOf(j), obj);
				j++;
			}
			content.add(rows);
		}
		return content;
	}

	@SuppressWarnings({"unchecked", "rawtypes", "hiding"})
	public static <T> List<T> readExcelContent(Class<T> clazz, InputStream is, String fileType) throws Exception {
		Workbook wb = null;
		try {
			if ("xls".equals(fileType)) {
				wb = new HSSFWorkbook(is);
			} else if ("xlsx".equals(fileType)) {
				wb = new XSSFWorkbook(is);
			} else {
				wb = null;
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}

		if (wb == null) {
			throw new Exception("Workbook对象为空！");
		}

		List<T> dist = new ArrayList<>();
		// 得到目标目标类的所有的字段列表
		Field filed[] = clazz.getDeclaredFields();
		//
		Map fieldmap = new HashMap();
		// 循环读取所有字段
		for (Field f : filed) {
			// 得到单个字段上的Annotation
			ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
			// 如果标识了Annotationd的话
			if (exa != null) {
				// 构造设置了Annotation的字段的Setter方法
				String fieldName = f.getName();
				String setMethodName = "set"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				// 构造调用的method，
				Method setMethod = clazz.getMethod(setMethodName, f.getType());
				// 将这个method以Annotaion的名字为key来存入。
				if (ValidateUtils.notEmpty(exa.exportName())) {
					fieldmap.put(exa.exportName(), setMethod);
				} else {
					fieldmap.put(fieldName, setMethod);
				}
			}
		}

		// 得到第一页
		Sheet sheet = wb.getSheetAt(0);
		// 得到第一面的所有行
		Iterator<Row> row = sheet.rowIterator();

		// 得到第一行，也就是标题行
		Row title = row.next();
		// 得到第一行的所有列
		Iterator<Cell> cellTitle = title.cellIterator();
		// 将标题的文字内容放入到一个map中。
		Map titlemap = new HashMap();
		// 从标题第一列开始
		int i = 0;
		// 循环标题所有的列
		while (cellTitle.hasNext()) {
			Cell cell = cellTitle.next();
			String value = cell.getStringCellValue();
			// 还是把表头trim一下
			value = value.trim();
			titlemap.put(i, value);
			i = i + 1;
		}
		/**
		 * 解析内容行
		 */
		// 用来格式化日期的DateFormat
		while (row.hasNext()) {
			// 标题下的第一行
			Row rown = row.next();
			T tObject = clazz.newInstance();
			for (int k = 0; k < titlemap.size(); k++) {
				Cell cell = rown.getCell(k);
				String titleString = (String) titlemap.get(k);
				if (fieldmap.containsKey(titleString)) {
					Method setMethod = (Method) fieldmap.get(titleString);
					//转换excel列到字段
					boolean isSuccess = cellConvertColumn(cell, setMethod, tObject);
					// 解析失败，程序不继续往下走
					if (!isSuccess) {
						throw new Exception(String.format("第 %d 行第 %d 列值 %s 解析失败", cell.getRowIndex() + 1,
								cell.getColumnIndex() + 1, cell.getStringCellValue()));
					}
				}
			}
			dist.add(tObject);
		}

		return dist;
	}

	/**
	 * @param cell
	 * @param setMethod
	 * @param object
	 */
	public static boolean cellConvertColumn(Cell cell, Method setMethod, Object object) {
		// 得到setter方法的参数
		Type[] ts = setMethod.getGenericParameterTypes();
		// 只要一个参数
		String xclass = ts[0].toString();
		// 判断参数类型
		try {
			// 检查时间格式
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
						setMethod.invoke(object, (Date) cell.getDateCellValue());
					} else if (xclass.equals("class java.lang.String")) {
						if ((cell.getNumericCellValue() + "").indexOf(".") > 0) {
							setMethod.invoke(object, (cell.getNumericCellValue() + "")
									.substring(0, (cell.getNumericCellValue() + "").lastIndexOf(".")));
						}
					} else if (xclass.equals("class java.lang.Integer")) {
						setMethod.invoke(object, (int) cell.getNumericCellValue());
					} else if (xclass.equals("class java.lang.Double")) {
						setMethod.invoke(object, (double) cell.getNumericCellValue());
					} else if (xclass.equals("class java.lang.Long")) {
						setMethod.invoke(object, (long) cell.getNumericCellValue());
					} else if (xclass.equals("int")) {
						setMethod.invoke(object, (int) cell.getNumericCellValue());
					}
					break;
				case HSSFCell.CELL_TYPE_STRING: // 字符串
					if (xclass.equals("class java.lang.Integer")) {
						setMethod.invoke(object, Integer.parseInt(cell.getStringCellValue()));
					} else if (xclass.equals("class java.lang.Long")) {
						setMethod.invoke(object, Long.parseLong(cell.getStringCellValue()));
					} else if (xclass.equals("class java.lang.String")) {
						setMethod.invoke(object, cell.getStringCellValue().trim());
					} else if (xclass.equals("int")) {
						int temp = Integer.parseInt(cell.getStringCellValue());
						setMethod.invoke(object, temp);
					} else if (xclass.equals("class java.util.Date")) {// 处理日期格式、时间格式
						setMethod.invoke(object, (Date) cell.getDateCellValue());
					}
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
					Boolean boolname = true;
					if (cell.getStringCellValue().equals("否")) {
						boolname = false;
					}
					setMethod.invoke(object, boolname);
					break;
				case HSSFCell.CELL_TYPE_BLANK: // 为空
					break;
				default:
					logger.error("没有可用的类型");
			}
		} catch (Exception e) {// 转换出错
			logger.error("Excel parse failed.", e);
			return false;
		}
		return true;
	}

	/**
	 * 根据Cell类型设置数据
	 *
	 * @param cell
	 */
	private Object getCellFormatValue(Cell cell) {
		Object cellvalue;
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
				case Cell.CELL_TYPE_FORMULA: {
					// 判断当前的cell是否为Date
					if (DateUtil.isCellDateFormatted(cell)) {
						// 如果是Date类型则，转化为Data格式
						// data格式是带时分秒的：2013-7-10 0:00:00
						// cellvalue = cell.getDateCellValue().toLocaleString();
						// data格式是不带时分秒的：2013-7-10
						cellvalue = cell.getDateCellValue();
					} else {// 如果是纯数字
						// 取得当前Cell的数值
						cellvalue = (int) cell.getNumericCellValue();
					}
					break;
				}
				case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
					// 取得当前的Cell字符串
					cellvalue = cell.getRichStringCellValue().getString();
					break;
				default:// 默认的Cell值
					cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
}
