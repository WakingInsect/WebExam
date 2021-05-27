package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.example.demo.core.HttpServer;


/**
 * 程序的入口
 */
public class Main {
    public static void main(String[] args) {
		System.out.println("Welcome use this program!");
		HttpServer server = new HttpServer();
        server.await();
    }
    
    public void properties() {
		Properties prop = new Properties();
		try (InputStream in = this.getClass().getResourceAsStream("/config.properties")) {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}