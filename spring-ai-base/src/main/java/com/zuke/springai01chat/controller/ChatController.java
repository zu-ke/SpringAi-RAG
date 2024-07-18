package com.zuke.springai01chat.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    @Resource
    private ChatClient chatClient;

    //chatModel所有模型通用，但是缺少模型特性
    @Resource
    private ChatModel chatModel;

    @RequestMapping("/chat1")
    public Object chat1(@RequestParam("msg") String msg) {
        String output = chatClient.prompt().user(msg).call().content();
        return output;
    }

    // 流式api（推荐）
    @RequestMapping(value = "/chat2",produces = "text/html;charset=utf-8")
    public Flux<String> chat2(@RequestParam("msg") String msg) {
        Flux<String> output = chatClient.prompt()
                .user(msg)
                .stream()
                .content();
        return output;
    }

    @RequestMapping(value = "/chat3",produces = "text/html;charset=utf-8")
    public String chat3(@RequestParam("msg") String msg) {
        ChatResponse output = chatModel.call(new Prompt(msg, OpenAiChatOptions.builder().withTemperature(0.4f).build()));
        return output.getResult().getOutput().getContent();
    }
}
