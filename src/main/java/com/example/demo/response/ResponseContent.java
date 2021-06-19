package com.example.demo.response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.example.demo.config.GlobalConfig;
import com.example.demo.log.Log;

public class ResponseContent {

    private StringBuilder content = new StringBuilder();

    public byte[] header(String resource) {
        try (FileInputStream input = new FileInputStream(GlobalConfig.getInstance().getResource() + "/" + resource)) {
            // 写入响应头
            setVersion(200);
            setType(resource);
            setLength(input);
            return content.toString().getBytes();
        } catch (FileNotFoundException e) {
            Log.log("访问的文件不存在");
            setVersion(404);
            setType("html");
            
            return content.toString().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return content.toString().getBytes();
    }
    public byte[] body(String resource) {
        try (FileInputStream input = new FileInputStream(GlobalConfig.getInstance().getResource() + "/" + resource)) {
            return input.readAllBytes();
        } catch (FileNotFoundException e) {
            Log.log("访问的文件不存在");
            return notFound();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setVersion(int code) {
        switch (code) {
            case 200:
                content.append("HTTP/1.1 200 OK\n");
                break;
            case 404:
                content.append("HTTP/1.1 404 NOT FOUND\n");
                break;
        }

    }

    private void setType(String resource) {
        if (resource.endsWith("html"))
            content.append("Content-Type: text/html\n");
        else if (resource.endsWith("ico") || resource.endsWith("jpg"))
            content.append("Content-Type: image/jpeg\n");

    }

    private void setLength(InputStream input) throws IOException {
        content.append("Content-Length: " + input.available() + "\n");
        content.append("\n");
    }

    private byte[] notFound() {
        String content = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\">"
                + "<link rel=\"shortcut icon\" href=\"favicon.ico\" /><title>Not Found</title></head>"
                + "<body><center><h2>File Not Found</h2></center></body></html>";
        return content.getBytes();

    }
}
