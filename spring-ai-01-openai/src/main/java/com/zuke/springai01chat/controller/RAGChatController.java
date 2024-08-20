package com.zuke.springai01chat.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/chat")
public class RAGChatController {

    @Value("classpath:file/rag.st")
    private Resource promptResource;
    @Autowired
    private ChatModel chatModel;
    @Autowired
    private VectorStore vectorStore;

    @GetMapping("/simple")
    public String simpleChat(String prompt) {
        return chatModel.call(prompt);
    }

    @GetMapping("/rag")
    public String ragChat(String prompt){
        // 从向量数据库中搜索相似文档
        List<Document> documents = vectorStore.similaritySearch(prompt);
        // 获取documents里的content
        List<String> context = documents.stream().map(Document::getContent).toList();
        // 创建系统提示词
        SystemPromptTemplate promptTemplate = new SystemPromptTemplate(promptResource);
        // 填充数据
        Prompt p = promptTemplate.create(Map.of("context", context, "question", prompt));

        ChatResponse response = chatModel.call(p);
        AssistantMessage aiMessage = response.getResult().getOutput();
        return aiMessage.getContent();
    }

}
