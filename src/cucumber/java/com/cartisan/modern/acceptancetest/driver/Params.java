package com.cartisan.modern.acceptancetest.driver;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class Params {
    public static final String PARAM_CONNECTOR = "=";
    public static final String QUERY_HEAD = "?";
    public static final String PARAM_DELIMITER = "&";
    private final Map<String, String> params = new HashMap<>();

    public void add(String name, String value){
        params.put(name, value);
    }

    public String getQuery(){
        if (params.isEmpty())
            return "";

        return QUERY_HEAD +params.entrySet().stream()
                .map(this::paramInQuery)
                .collect(joining(PARAM_DELIMITER));
    }

    private String paramInQuery(Map.Entry<String, String> param) {
        return param.getKey()+ PARAM_CONNECTOR +param.getValue();
    }
}
