package com.example.recovery.framework.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code Object} is .
 *
 * @author MSI
 * @version 1.0
 */
public class ResponseMap {

    private static Map<String,Object> result = new HashMap<>();

    public static Map<String, Object> factoryResult(String code, Object data){
        result.put("code",code);
        result.put("data",data);
        return result;
    }

    public static Map<String, Object> factoryResult(String code, Object data, Object total){
        result.put("code",code);
        result.put("data",data);
        result.put("total",total);
        return result;
    }
}
