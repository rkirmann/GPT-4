package com.gpt.dto.image.response;

import java.util.List;
import lombok.Data;

@Data
public class ImageResponse{
	private List<DataItem> data;
	private Integer created;
}
