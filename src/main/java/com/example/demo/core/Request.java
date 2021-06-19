package com.example.demo.core;

import java.io.IOException;
import java.io.InputStream;

import com.example.demo.log.Log;


public class Request {

    private String uri; // �������Դ��ַ

    /**
     * ��������н���
     */
    public void parse(InputStream input) {
        StringBuffer request = new StringBuffer(2048);
        byte[] buffer = new byte[2048];
        try {
            int i = input.read(buffer);
            for (int j = 0; j < i; ++j) 
            request.append((char) buffer[j]);
        uri = parseUri(request.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * �Ե�ַ���н���
     * @param requestString request�ַ���
     * @return
     */
    public String parseUri(String requestString) {
        Log.log("��ӡ������:");
        System.out.println(requestString);
        Log.log("*********************");
        // ��ȡ���ʵ��ļ���
        String[] msg = requestString.split(" ");
        if (msg != null && msg.length != 0) {
            if(msg.length==1)
                return "";
            return msg[1].substring(1);
        }
        return null;
    }

    public String getUri() {
        return this.uri;
    }
}
