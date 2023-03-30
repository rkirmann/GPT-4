package com.gpt.dto.image.variation;

import java.util.List;
import lombok.Data;

@Data
public class ImageVariationResponse{
	private List<DataItem> data;
	private Integer created;
}
