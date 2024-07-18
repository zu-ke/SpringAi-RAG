package com.zuke.springai01chat.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class TextToAudioController {

    @Resource
    private OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @RequestMapping("/tts")
    public String tts() {
        OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
                .withModel("tts-1")
                .withVoice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
                .withResponseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .withSpeed(1.0f)
                .build();

        SpeechPrompt speechPrompt = new SpeechPrompt("Hello, 我是租客", speechOptions);
        SpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);

        byte[] output = response.getResult().getOutput();

        // 将byte[]存为MP3文件
        try {
            writeByteArrayToMp3(output, "tts", ".mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void writeByteArrayToMp3(byte[] audioByte, String prefix, String suffix) throws IOException {
        File tempFile = File.createTempFile(prefix, suffix);
        System.out.println("文件路径 ==> " + tempFile.getAbsolutePath());
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        fileOutputStream.write(audioByte);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static void deleteFile(File file) {
        if (file.delete()) {
            System.out.println("文件已删除：" + file.getAbsolutePath());
        } else {
            System.out.println("文件删除失败：" + file.getAbsolutePath());
        }
    }

}
