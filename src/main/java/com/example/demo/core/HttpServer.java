package com.example.demo.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.demo.config.GlobalConfig;

public class HttpServer {

	private boolean shutdown = false;

	public void await() {
		GlobalConfig config = GlobalConfig.getInstance();
		try (ServerSocket serverSocket = new ServerSocket(config.getPort(), 1, InetAddress.getByName(config.getHost()))) {
			while (!shutdown) {
				try (Socket socket = serverSocket.accept();
						InputStream input = socket.getInputStream();
						OutputStream output = socket.getOutputStream()) {
					Request request = new Request();
					request.parse(input);
					String uri = request.getUri();
					Response response = new Response(output);
					response.sendStaticResource(uri);
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
}
