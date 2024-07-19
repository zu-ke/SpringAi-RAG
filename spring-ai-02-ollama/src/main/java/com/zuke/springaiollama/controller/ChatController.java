package com.zuke.springaiollama.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Resource
    private OllamaChatModel ollamaChatModel;

    @RequestMapping("/chat1")
    public String chat1(@RequestParam(value = "msg",defaultValue = "你可以干嘛？") String msg){
        ChatResponse output = ollamaChatModel.call(new Prompt(msg));
        return output.getResult().getOutput().getContent();
    }

    @RequestMapping("/chat2")
    public String chat2(@RequestParam(value = "msg",defaultValue = "你可以干嘛？") String msg){
        ChatResponse output = ollamaChatModel.call(new Prompt(msg, OllamaOptions.create()
                .withModel("gemma2:latest")
                .withTemperature(0.4f)
        ));
        return output.getResult().getOutput().getContent();
    }
}
