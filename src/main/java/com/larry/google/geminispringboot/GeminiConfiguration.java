package com.larry.google.geminispringboot;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.ChatSession;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class GeminiConfiguration {
    @Bean
    public VertexAI vertexAI() {
        return new VertexAI("neural-reactor-416201", "us-central");
    }

    @Bean
    public GenerativeModel generativeModel(VertexAI vertexAI) {
        return new GenerativeModel("gemini-pro-vision", vertexAI);
    }

    @Bean
    public GenerativeModel geminiProGenerativeModel(VertexAI vertexAI) {
        return new GenerativeModel("gemini-pro", vertexAI);
    }

    @Bean
    @SessionScope
    public ChatSession chatSession(@Qualifier("geminiProGenerativeModel") GenerativeModel generativeModel) {
        return new ChatSession(generativeModel);
    }
}
