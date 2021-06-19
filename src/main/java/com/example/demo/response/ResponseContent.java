package com.example.demo.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.example.demo.config.GlobalConfig;

public class ResponseContent {

    private static final int BUFFER_SIZE = 1024;

    private StringBuilder content = new StringBuilder();

    public String header(String rescource) {
        byte[] bytes = new byte[BUFFER_SIZE];
        try (InputStream input = new FileInputStream(new File(GlobalConfig.getInstance().getResource(), rescource))) {
            // 写入响应头
            setVersion();
            setType(rescource);
            setLength(input);
            int ch = input.read(bytes, 0, BUFFER_SIZE);
            while (ch != -1) {
                content.append(new String(bytes));
                ch = input.read(bytes, 0, BUFFER_SIZE);
            }
            return content.toString();
        } catch (FileNotFoundException e) {
            // 如果文件不存在，则输出404
            e.printStackTrace();
            String errorMessage = "HTTP/1.1 404  Not Found\r\n" + "Content-Type:text/html\r\n" + "Content-Length:23\r\n"
                    + "\r\n" + "<h1>File Not Found</h1>";
            content.append(errorMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private void setVersion() {
        content.append("HTTP/1.1 200 OK\r\n");
    }

    private void setType(String resource) {
        if(resource.endsWith("html"))
            content.append("Content-Type:text/html\r\n");
        else if(resource.endsWith("ico"))
            content.append("Content-Type:image/jpeg\r\n");

    }

    private void setLength(InputStream input) throws IOException {
        content.append("Content-Length:" + input.available()+"\r\n");
    }
    

}
