package com.gpt.dto.chat.response;

import lombok.Data;

@Data
public class Usage{
	private Integer completionTokens;
	private Integer promptTokens;
	private Integer totalTokens;
}
