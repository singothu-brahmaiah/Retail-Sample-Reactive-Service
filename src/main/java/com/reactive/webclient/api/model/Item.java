package com.reactive.webclient.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	private Integer itemId;
	private String itemName;
	private Integer orderUserId;
	
}
