package com.ecom.dto;


import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDto {
	
//	private int product_id;
//	private String name;
//	private String desc;
//	private String title;
//	private double unit_price;
//	private String image_url;
//	private String active;
//	private int units_stock;
//	private String date_created;
//	private String last_updated;
	
	private Long id;
	private String name;
	private String description;
	private String title;
	private BigDecimal unitPrice;
	private String imageUrl;
	private Boolean active;
	private int unitsInStock;
	private String dateCreated;
	private String lastUpdate;
	
	
	
}
