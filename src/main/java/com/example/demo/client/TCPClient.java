package com.example.demo.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        new TCPClient().Start("");
    }

    public void Start(String address) {

        if (address == null || address.length() < 1)
            address = "localhost";

        try (Socket socket = new Socket("localhost", 8189);
                Scanner scan = new Scanner(socket.getInputStream(), "gbk"); // 接收套接字中的消息
                Scanner type = new Scanner(System.in); // 接收用户键盘输入
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "gbk"), true)) {

            boolean done = false;
            while (!done) {
                String line = scan.nextLine();
                if (line != null) {
                    System.out.println("服务器消息:\t"+line);
                    if ("end!".equals(line.trim())) {
                        System.out.println("程序即将终止，感谢您的使用");
                        done = true;
                    } else {
                        String msg = type.nextLine();
                        if (msg != null)
                            writer.println(msg);
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

