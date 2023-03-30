package com.gpt.dto.image.request;

import lombok.Data;

@Data
public class ImageRequest {
    private String message;
    private String size;
    private int amount;
}
