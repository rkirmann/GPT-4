package com.gpt.dto.chat.response;

import lombok.Data;

@Data
public class ChoicesItem{
	private String finishReason;
	private Integer index;
	private Message message;
}
