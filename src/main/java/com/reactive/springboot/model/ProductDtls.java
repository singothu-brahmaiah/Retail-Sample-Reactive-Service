package com.reactive.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtls{

	private String id;
    private String name;
    private int qty;
    private double price;
		
	
}
