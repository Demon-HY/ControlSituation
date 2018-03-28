package com.control.situation.utils.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * HTTP 请求相关操作类
 *
 * Created by demon on 2017/7/1 0001.
 */
public class HttpUtils {

    private static final String UTF_8 = "UTF-8";

    /**
     * url 编码
     *
     * @param text
     * @param charset
     * @return String
     */
    public static String encodeURL(String text, String charset) {
        try {
            return URLEncoder.encode(text, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 默认utf-8的url编码
     *
     * @param text
     * @return
     */
    public static String encodeURL(String text) {
        return encodeURL(text, UTF_8);
    }

    /**
     * url 解码
     *
     * @param text
     * @param charset
     * @return String
     */
    public static String decodeURL(String text, String charset) {
        try {
            return URLDecoder.decode(text, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 默认utf-8的url解码
     *
     * @param text
     * @return String
     */
    public static String decodeURL(String text) {
        return decodeURL(text, UTF_8);
    }

    /**
     * test if URL is connect
     * @param urlStr
     * @return
     */
    public static synchronized boolean ping(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return false;
        }
        while (counts < 3) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                int state = con.getResponseCode();
                if (state == 200) {
                    return true;
                }
                break;
            }catch (Exception ex) {
                counts++;
            }
        }
        return false;
    }

    /**
     * get URL domain(域名)
     * @param urlStr
     * @return exception return null
     */
    public static String getDomainFromUrl(String urlStr) {
        String domainUrl;
        try {
            URL url = new URL(urlStr);
            domainUrl = url.getProtocol() + "://" + url.getAuthority() + "/";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return domainUrl;
    }

    /**
     * get URL host
     * @param urlStr
     * @return exception return null
     */
    public static String getHost(String urlStr){
        String host;
        try {
            URL url = new URL(urlStr);
            host=url.getHost();
        } catch (Exception e) {
            return null;
        }
        return host;
    }

    public static void main(String[] args) {
        System.out.println(ping("http://www.baidu.com/"));
        System.out.println(getDomainFromUrl("http://www.baidu.com/index.html?a=1"));
        System.out.println(getHost("http://www.baidu.com/"));
    }
}
