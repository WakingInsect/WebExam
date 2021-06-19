package com.example.demo;

import com.example.demo.config.GlobalConfig;
import com.example.demo.core.HttpServer;
import com.example.demo.log.Log;


/**
 * 程序的入口
 */
public class Main {
    public static void main(String[] args) {
		Log.log("正在启动程序");
        HttpServer server = new HttpServer();
        Log.log("正在初始化数据");
        GlobalConfig.getInstance();
        // 开始监听连接
        server.await();
    }
}