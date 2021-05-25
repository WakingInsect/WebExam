package com.example.demo.httpserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 服务器响应类。
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {

        byte[] bytes = new byte[BUFFER_SIZE];
        try (InputStream input = this.getClass().getResourceAsStream(request.getUri())) {
            // 写入响应头
            String respHeader = "HTTP/1.1 200 OK\r\n" + "Content-Type:text/html\r\n" + "Content-Length:"
                    + input.available() + "\r\n" + "\r\n";
            output.write(respHeader.getBytes());
            int ch = input.read(bytes, 0, BUFFER_SIZE);
            while (ch != -1) {
                output.write(bytes, 0, BUFFER_SIZE);
                ch = input.read(bytes, 0, BUFFER_SIZE);
            }
        } catch (FileNotFoundException e) {
            // 如果文件不存在，则输出404
            e.printStackTrace();
            String errorMessage = "HTTP/1.1 404  Not Found\r\n" + "Content-Type:text/html\r\n" + "Content-Length:23\r\n"
                    + "\r\n" + "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
