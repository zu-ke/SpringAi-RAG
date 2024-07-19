package com.zuke.springai01chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAi01ChatApplication {

    public static void main(String[] args) {
        //如果想用openai官方的话，需要加上代理
        // String proxy = "127.0.0.1"; //填写具体的代理ip
        // int port = 8080; //代理端口
        // System.setProperty("proxyType", "4");
        // System.setProperty("proxyPort", Integer.toString(port));
        // System.setProperty("proxyHost", proxy);
        // System.setProperty("proxySet", "true");

        SpringApplication.run(SpringAi01ChatApplication.class, args);
    }

}
