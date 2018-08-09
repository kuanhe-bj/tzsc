package io.renren.vas.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


/**
 * Json工具类
 *
 * @author
 * @date 2015年1月13日
 */
public class JsonUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectmapper = new ObjectMapper();

    /**
     * 把任何对象转换成Json
     *
     * @param object
     * @return Json字符串
     */
    public static String objectToJson(Object object) {
        Writer strWriter = new StringWriter();
        try {
            objectmapper.writeValue(strWriter, object);
        } catch (Exception e) {
            logger.error("JSON解析异常:", e);
        }
        String json = strWriter.toString();
        return json;
    }


    /**
     * 把JSON串转换成实体类
     *
     * @param object 需要转换的实体对象
     * @param json   需要转换的JSON串
     * @return 实体对象
     */
    public static Object jsonToEntity(Object object, String json) {
        try {
            object = objectmapper.readValue(json, object.getClass());
            return object;
        } catch (Exception e) {
            logger.error("JSON解析异常:", e);
            return null;
        }
    }

    /**
     * Json串转Object
     *
     * @param json  需要转的JSON
     * @param clazz 需要转换的Object
     * @return 实体对象
     */
    public static Object jsonToObject(String json, Class clazz) {
        Object obj = null;
        try {
            obj = objectmapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            logger.error("JSON解析异常:", e);
        } catch (JsonMappingException e) {
            logger.error("JSON解析异常:", e);
        } catch (IOException e) {
            logger.error("JSON解析异常:", e);
        }
        return obj;
    }

    /**
     * 根据传入的KEY从JSON串中取出对应的VALUE
     *
     * @param json
     * @param key
     * @return
     */
    public static String jsonToStringByKey(String json, String key) {
        try {
            JsonNode rootNode = objectmapper.readTree(json);
            String value = rootNode.path(key).asText();
            return value;
        } catch (Exception e) {
            logger.error("JSON解析异常:", e);
            return null;
        }
    }

    /**
     * Json串转换成Map
     *
     * @param json
     * @return
     */
    public static Map toMap(String json) {
        try {
            return objectmapper.readValue(json, Map.class);
        } catch (Exception e) {
            logger.error("JSON解析异常:", e);
            return null;
        }
    }



}
