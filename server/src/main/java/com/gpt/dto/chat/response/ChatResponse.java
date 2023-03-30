package com.gpt.dto.chat.response;

import java.util.List;
import lombok.Data;

@Data
public class ChatResponse{
	private Integer created;
	private Usage usage;
	private String model;
	private String id;
	private List<ChoicesItem> choices;
	private String object;
}
