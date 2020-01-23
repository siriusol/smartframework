package org.smart4j.framework.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JSON 工具类
 * @author Ther
 */
public class JSONUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * POJO to JSON
     * @param obj
     * @param <U>
     * @return
     */
    public static <U> String toJSON(U obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * POJO to JSON
     */
    public static <U> U fromJSON(String json, Class<U> type) {
        U pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            LOGGER.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
