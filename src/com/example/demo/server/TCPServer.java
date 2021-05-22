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
        // ʹ���׽��ּ����˿�
        try (ServerSocket s = new ServerSocket(8189)) {
            // ��ȡ���ͻ��˷���
            try (Socket incoming = s.accept();
                    InputStream inStream = incoming.getInputStream();
                    OutputStream outStream = incoming.getOutputStream();
            ){
                // �Ϳͻ��˽��н���
                try (Scanner in = new Scanner(inStream,"gbk"); // ��ȡ�׽����е����� 
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "gbk"), true)) {
                        
                    out.println("Type in Bye to stop this program!"); // ��ͻ��˴�ӡ��ʾ���
                    boolean done = false;   // ������ֹ����
                    while(!done&&in.hasNextLine()){
                        String line = in.nextLine();
                        if (line.trim().equals("Bye")) {
                            done = true;
                            out.println("end!");
                        } else {
                            out.println("over");
                        }
                        System.out.println("���Կͻ��˵���Ϣ:\t" + line);                        
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
