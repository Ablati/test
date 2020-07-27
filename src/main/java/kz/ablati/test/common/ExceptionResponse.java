package kz.ablati.test.common;

import java.util.HashMap;
import java.util.Map;

public class ExceptionResponse {

    public static Map<String, Object> create(String message, int code){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", code);
        response.put("code", code);
        return response;
    }
}
