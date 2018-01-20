package com.control.situation.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rwrwd7 on 2016/12/21.
 */
public class JsonUtil {

    private static Gson gson = null;

    static{
        gson  = new Gson();//todo yyyy-MM-dd HH:mm:ss
    }

    public static synchronized Gson newInstance(){
        if(gson == null){
            gson =  new Gson();
        }
        return gson;
    }

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T toBean(String json,Class<T> clz){

        return gson.fromJson(json, clz);
    }

    public static <T> List<T> toList(String json, Class<T> clz){
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        List<T> list  = new ArrayList<>();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem, clz));
        }
        return list;
    }

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将 Java 对象转为 JSON 字符串
     */
    public static <T> String toJSON(T obj) {
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOG.error("Java 转 JSON 出错！", e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * 将 JSON 字符串转为 Java 对象
     */
    public static <T> T fromJSON(String json, Class<T> type) {
        T obj;
        try {
            objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            obj = objectMapper.readValue(json, type);
        } catch (Exception e) {
            LOG.error("JSON 转 Java 出错！", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Map<String, Object> jsonToMap(String json)throws IOException{
        return objectMapper.readValue(json, Map.class);
    }

    public static void sendJsonResponse(HttpServletResponse response, Object obj)
            throws Exception {
        sendJsonResponse(response, obj, "");
    }

    public static void sendJsonResponse(HttpServletResponse response, Object obj, String callback)
            throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        // 声明接受所有域的请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        if(StringUtils.isNotBlank(callback)){
            writeJavascript(response, callback, convertToJSON(obj));
        }else{
            response.getWriter().print(convertToJSON(obj));
        }
        out.flush();
    }

    /**
     *
     * @author inning
     * @Date 2015-7-30 下午7:34:16
     * @param response
     * @param callback
     * @param json
     * @throws Exception
     */
    public static void writeJavascript(HttpServletResponse response, String callback, String json) throws Exception {
        PrintWriter pw = null;
        try {
            response.setContentType("text/javascript;charset=utf-8");
            pw = response.getWriter();

            pw.write(callback + "(" + json + ");");
        } catch (Exception e) {
            throw e;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    /**
     * 描述:转换成JSON数据类型方法
     *
     * @param obj
     *            : 实体对象
     * @return JSON: json类型文本数据
     * **/
    public static String convertToJSON(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }


}
