package com.gpt.controller;


import com.gpt.dto.chat.request.ChatDto;
import com.gpt.dto.image.request.ImageRequest;
import com.gpt.dto.image.response.ImageResponse;
import com.gpt.dto.image.variation.ImageVariationResponse;
import com.gpt.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {

    @Autowired
    private OpenAiService openAiService;

    @PostMapping(value = "chat")
    public Map<String, String> getSectors(@RequestBody ChatDto chatDto) {
        return Map.of("response", openAiService.chat(chatDto.getMessage(), chatDto.getTemperature())) ;
    }

    @PostMapping(value = "image")
    public ImageResponse generateImage(@RequestBody ImageRequest imageRequest) {
        return openAiService.generateImages(imageRequest.getMessage(), imageRequest.getAmount(), imageRequest.getSize());
    }

    @PostMapping("image-variations")
    public ImageVariationResponse getImageVariations(
            @RequestParam("image") MultipartFile image,
            @RequestParam("n") int n,
            @RequestParam("size") String size) throws IOException {

        return openAiService.getImageVariations(image, n, size);
    }

}
