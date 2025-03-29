package com.ecom.mapper;

import java.util.function.Function;

import org.modelmapper.ModelMapper;

import com.ecom.dto.ProductDto;
import com.ecom.entity.Product;

public class ProductMapper {
	public static final ModelMapper modelMapper=new ModelMapper();
	
	public static ProductDto convertToDto(Product entity) {
		return modelMapper.map(entity, ProductDto.class);
	}
	
	public static Product convertToentity(ProductDto dto) {
		return modelMapper.map(dto, Product.class);
	}
}
