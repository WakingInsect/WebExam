package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应行中使用的状态码及其描述的常量类
 */
public final class StatusCode {
    private final static int OK = 200;
    private final static int MOVED_PERMANENTLY = 301;
    private final static int FOUND = 302;
    private final static int NOT_FOUND = 404;
    private final static int INTERNAL_SERVER_ERROR = 500;

    private final static Map<Integer,String> desc = new HashMap<Integer,String>(){
        {
            put(200, "OK");
            put(301, "MOVED PERMANENTLY");
            put(302, "FOUND");
            put(404, "NOT FOUND");
            put(500,"INTERNAL SERVER ERROR");
        }
    };
}