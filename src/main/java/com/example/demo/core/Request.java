package com.example.demo.core;

import java.io.IOException;
import java.io.InputStream;


public class Request {

    private String uri; // 请求的资源地址

    /**
     * 对请求进行解析
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
     * 对地址进行解析
     * @param requestString request字符串
     * @return
     */
    public String parseUri(String requestString) {
        System.out.println(requestString);
        // 获取访问的文件名
        String[] msg = requestString.split(" ");
        if (msg != null && msg.length != 0) {
            return msg[1].substring(1);
        }
        return null;
    }

    public String getUri() {
        return this.uri;
    }
}
