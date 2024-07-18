package com.zuke.springai01chat.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FunctionCallController {

    @Resource
    private OpenAiChatModel openAiChatModel;

    // 应对大模型无法获取实时信息的弊端，结合Function bean使用
    @RequestMapping("/fctcall")
    public String tts(@RequestParam(value = "msg", defaultValue = "成都今天天气怎么样") String msg) throws IOException {
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .withFunction("locationNameFunction") //实现了Function接口的beanName
                 .build();
        ChatResponse output = openAiChatModel.call(new Prompt(msg, chatOptions));
        return output.getResult().getOutput().getContent();
    }


}
