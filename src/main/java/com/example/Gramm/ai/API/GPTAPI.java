package com.example.Gramm.ai.API;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class GPTAPI {

    private final RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String apiKey;

    public GPTAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String ask(String prompt) {

        String endpoint = "https://api.openai.com/v1/chat/completions";

        System.out.println(prompt);

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.set("Authorization", "Bearer " + apiKey);
        requestHeader.set("Content-Type", "application/json");

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", Arrays.asList(message));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, requestHeader);

        ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode choices = root.path("choices");
            JsonNode messageNode = choices.get(0).path("message");
            String ResMessage = messageNode.path("content").textValue();
            return ResMessage;
            
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
