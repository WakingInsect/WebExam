package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.example.demo.httpserver.HttpServer;


/**
 * 程序的入口
 */
public class Main {
    public static void main(String[] args) {
		System.out.println("Welcome use this program!");
		Main main = new Main();
		HttpServer server = new HttpServer();
		main.properties();
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