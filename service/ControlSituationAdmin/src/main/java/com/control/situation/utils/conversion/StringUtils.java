package com.control.situation.utils.conversion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private StringUtils() {}

	//判断字符串是否为数字的方法
	public static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}

	// 判断一个字符串是否都为数字
	public static boolean isDigit(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}
}


