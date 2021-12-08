package com.reactive.springboot.dto;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtls{
	
	@Id
	private String id;
    private String name;
    private int qty;
    private double price;	
	
}
