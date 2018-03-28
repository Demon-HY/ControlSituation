package com.control.situation.utils.http;

import com.control.situation.utils.ValidateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClient {

	/**
	 * 发送GET请求
	 *
	 * @param url 目的地址
	 */
	public static String sendGet(String url) {
		return sendGet(url, null);
	}

	/**
	 * 发送GET请求
	 *
	 * @param url        目的地址
	 * @param parameters 请求参数，Map类型。
	 */
	public static String sendGet(String url, Map<String, String> parameters) {
		StringBuilder result = new StringBuilder();
		BufferedReader in = null;// 读取响应输入流
		StringBuilder sb = new StringBuilder();// 存储参数
		String params;// 编码之后的参数
		try {
			String full_url = url;
			// 编码请求参数
			if (ValidateUtils.notEmpty(parameters)) {
				if (parameters.size() == 1) {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(
								java.net.URLEncoder.encode(parameters.get(name),
										"UTF-8"));
					}
					params = sb.toString();
				} else {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(
								java.net.URLEncoder.encode(parameters.get(name),
										"UTF-8")).append("&");
					}
					String temp_params = sb.toString();
					params = temp_params.substring(0, temp_params.length() - 1);
				}
				full_url = url + "?" + params;
			}

			// 创建URL对象
			URL connURL = new URL(full_url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 建立实际的连接
			httpConn.connect();
			// 响应头部获取
			Map<String, List<String>> headers = httpConn.getHeaderFields();
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	public static String sendPost(String url, byte[] param) {
		return sendPost(url, param, null, 10000);
	}

	public static String sendPost(String url, byte[] param, int timeout) {
		return sendPost(url, param, null, timeout);
	}

	public static String sendPost(String url, byte[] param, Map<String, String> headers) {
		return sendPost(url, param, headers, 10000);
	}


	public static String sendPost(String url, byte[] param, Map<String, String> headers, int timeout) {
		StringBuilder result = new StringBuilder();
		try {
			URL httpurl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpurl
					.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setConnectTimeout(timeout);
			if (null != headers) {
				Set<String> keys = headers.keySet();
				for (String key : keys) {
					String value = headers.get(key);
					httpConn.addRequestProperty(key, value);
				}
			}

			if (null != param) {
				OutputStream writer = httpConn.getOutputStream();
				writer.write(param);
				writer.flush();
				writer.close();
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
