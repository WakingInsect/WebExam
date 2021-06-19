package com.example.demo.core;

import java.io.IOException;
import java.io.OutputStream;

import com.example.demo.response.ResponseContent;

/**
 * ��������Ӧ�ࡣ
 */
public class Response {

    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void sendStaticResource(String rescource) throws IOException {
        ResponseContent content = new ResponseContent();
        String resp = content.header(rescource);
        output.write(resp.getBytes());
    }

}
