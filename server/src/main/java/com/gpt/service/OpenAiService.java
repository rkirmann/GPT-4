package com.gpt.service;

import com.gpt.dto.chat.response.ChatResponse;
import com.gpt.dto.image.response.ImageResponse;
import com.gpt.dto.image.variation.ImageVariationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
public class OpenAiService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String OPENAI_API_KEY;

    public String chat(String prompt, double temperature) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);

        String url = "https://api.openai.com/v1/chat/completions";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
        messages.add(message);

        requestBody.put("messages", messages);
        requestBody.put("temperature", temperature);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            var response = restTemplate.postForEntity(url, requestEntity, ChatResponse.class).getBody();
            return response.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }

    public ImageResponse generateImages(String prompt, int n, String size) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);

        String requestBody = String.format("{\"prompt\": \"%s\", \"n\": %d, \"size\": \"%s\"}", prompt, n, size);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForEntity("https://api.openai.com/v1/images/generations", entity, ImageResponse.class).getBody();
    }

    public ImageVariationResponse getImageVariations(MultipartFile image, int n, String size) throws IOException {
        String url = "https://api.openai.com/v1/images/variations";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
        // Convert MultipartFile to ByteArrayResource
        Resource imageResource = new ByteArrayResource(image.getBytes()) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }
        };
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", imageResource);
        body.add("n", n);
        body.add("size", size);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(url, requestEntity, ImageVariationResponse.class).getBody();
    }
}
