package com.gpt.dto.chat.request;

import lombok.Data;

@Data
public class ChatDto {
    private String message;
    private double temperature;
}
