package com.example.demo.utils;
/**
 * ��ȡ�ļ�·���Ĺ���
 */
public class PathUtil {
    public static <T> String getPath(Class<T> para) {
        return para.getResource("").getPath();
    }
}
