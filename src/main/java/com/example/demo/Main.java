package com.example.demo;

import com.example.demo.config.GlobalConfig;
import com.example.demo.core.HttpServer;
import com.example.demo.log.Log;


/**
 * ��������
 */
public class Main {
    public static void main(String[] args) {
		Log.log("������������");
        HttpServer server = new HttpServer();
        Log.log("���ڳ�ʼ������");
        GlobalConfig.getInstance();
        // ��ʼ��������
        server.await();
    }
}