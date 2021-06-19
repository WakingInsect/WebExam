package com.example.demo.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static void log(String msg) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println("["+format.format(date)+"]"+msg);
    }
}
