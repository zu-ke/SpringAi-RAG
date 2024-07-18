package com.zuke.springai01chat.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ImageController {

    @Resource
    private OpenAiImageModel openaiImageModel;

    @RequestMapping("/img")
    public String img(@RequestParam(value = "msg",defaultValue = "a cat") String msg) {
        ImageResponse response = openaiImageModel.call(
                new ImagePrompt(msg,
                        OpenAiImageOptions.builder()
                                .withModel(OpenAiImageApi.DEFAULT_IMAGE_MODEL) //模型 DALL_E_3
                                .withN(1) //生成多少张
                                .withHeight(1024) //宽
                                .withWidth(1024) //高
                                .build())

        );
        return response.getResult().getOutput().getUrl();
    }

}
