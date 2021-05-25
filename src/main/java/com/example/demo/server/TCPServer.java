package com.example.demo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {

    public static void main(String[] args) {
        new TCPServer().Start();
    }

    public void Start() {
        // 使用套接字监听端口
        try (ServerSocket s = new ServerSocket(8189)) {
            // 获取到客户端访问
            try (Socket incoming = s.accept();
                    InputStream inStream = incoming.getInputStream();
                    OutputStream outStream = incoming.getOutputStream();
            ){
                // 和客户端进行交互
                try (Scanner in = new Scanner(inStream,"gbk"); // 获取套接字中的输入 
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "gbk"), true)) {
                        
                    out.println("Type in Bye to stop this program!"); // 向客户端打印提示语句
                    boolean done = false;   // 程序终止条件
                    while(!done&&in.hasNextLine()){
                        String line = in.nextLine();
                        if (line.trim().equals("Bye")) {
                            done = true;
                            out.println("end!");
                        } else {
                            out.println("over");
                        }
                        System.out.println("来自客户端的消息:\t" + line);                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
