package com.example.demo.utils;
/**
 * 获取文件路径的工具
 */
public class PathUtil {
    public static <T> String getPath(Class<T> para) {
        return para.getResource("").getPath();
    }
}
