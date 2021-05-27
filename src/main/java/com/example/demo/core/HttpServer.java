package com.example.demo.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	private boolean shutdown = false;

	private int port;
	private String ip;

	public void await() {
		try (ServerSocket serverSocket = new ServerSocket(port, 1, InetAddress.getByName(ip))) {
			while (!shutdown) {
				try (Socket socket = serverSocket.accept();
						InputStream input = socket.getInputStream();
						OutputStream output = socket.getOutputStream()) {
					Request request = new Request(input);
					request.parse();
					Response response = new Response(output);
					response.setRequest(request);
					response.sendStaticResource();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public HttpServer(int port, String ip) {
		this.port = port;
		this.ip = ip;
	}
	
	public HttpServer() {
		this.port = 8080;
		this.ip = "127.0.0.1";
	}
}
