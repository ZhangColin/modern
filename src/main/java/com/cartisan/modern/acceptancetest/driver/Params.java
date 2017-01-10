package com.cartisan.modern.acceptancetest.driver;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class Params {
    private static final String QUERY_HEAD = "?";
    private static final String PARAM_DELIMITER = "&";
    private static final String PARAM_CONNECTOR = "=";
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
