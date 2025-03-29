package com.ecom.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderItemsDto {

	private Long id;
	private String imageUrl;
	private Double unitPrice;
	private Integer quantity;
	private String productName;
	 
	
}
