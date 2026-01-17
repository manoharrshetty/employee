package com.emp.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
Spring AI provides the following features:

Support for all major AI Model providers such as Anthropic, OpenAI, Microsoft, Amazon, Google, and Ollama
 */
@Service
public class AiServiceImpl<T> implements AiService<T> {
     private final ChatClient chatClient;

    @Autowired
    public AiServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
     }

    /**
     * The Chat Model API offers developers the ability to integrate AI-powered chat completion capabilities into their applications. It leverages pre-trained language models, such as GPT (Generative Pre-trained Transformer), to generate human-like responses to user inputs in natural language
     * @param request
     * @return
     */
    public T getResponse(String request, Class<T> type) {
        return chatClient.prompt()
                .user(request)
                .call()
                .entity(type);



    }
}
