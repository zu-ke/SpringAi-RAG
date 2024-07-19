package com.zuke.springai01chat.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class AiConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("你现在不是ChatGPT，我希望你是以一个全栈开发程序员来和我对话，你的名字叫做zukeChat")
                .build();
    }

}
