package com.gpt.controller;


import com.gpt.dto.chat.request.ChatDto;
import com.gpt.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {

    @Autowired
    private OpenAiService openAiService;

    @PostMapping(value = "chat")
    public Map<String, String> getSectors(@RequestBody ChatDto chatDto) {
        System.out.println(chatDto.toString());
        return Map.of("response", openAiService.chat(chatDto.getMessage(), chatDto.getTemperature())) ;
    }


}
