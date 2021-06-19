package com.example.demo.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import com.example.demo.response.ResponseContent;

/**
 * ��������Ӧ�ࡣ
 */
public class Response {

    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void sendStaticResource(String resource) throws IOException {
        ResponseContent content = new ResponseContent();
        byte[] header = content.header(resource);
        byte[] body = content.body(resource);
        System.out.println(Arrays.toString(body));
        output.write(header);
        output.write(body);
    }

}
