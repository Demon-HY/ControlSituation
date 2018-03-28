package com.control.situation.utils.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by demon on 2017/7/1 0001.
 */
public class HtmlUtils {

	/**
	 * get webpage charset
	 *
	 * @param content content text
	 * @return charset
	 */
	public static String matchCharset(String content) {
		Pattern p = Pattern.compile("(?<=charset=)(.+)(?=\")");
		Matcher m = p.matcher(content);
		if (m.find())
			return m.group();
		return null;
	}
}
