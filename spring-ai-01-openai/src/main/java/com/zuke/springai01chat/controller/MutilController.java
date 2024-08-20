package com.zuke.springai01chat.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class MutilController {

    @Resource
    private OpenAiChatModel openAiChatModel;

    @RequestMapping("/mutil")
    public String tts(@RequestParam(value = "msg", defaultValue = "你看到了什么") String msg) throws IOException {
        // 图片二进制流
        byte[] imageData = new ClassPathResource("/img/2.jpg").getContentAsByteArray();
        var userMessage = new UserMessage(
                msg,
                List.of(new Media(MimeTypeUtils.IMAGE_JPEG, imageData))
        );
        ChatResponse response = openAiChatModel.call(new Prompt(userMessage,
                OpenAiChatOptions.builder()
                        .withModel(OpenAiApi.ChatModel.GPT_4_O.getValue())
                        .build()));
        return response.getResult().getOutput().getContent();
    }


}
