package com.ecom.mapper;

import org.modelmapper.ModelMapper;

import com.ecom.dto.ProductCategoryDto;
import com.ecom.entity.ProductCategory;

public class ProductCateroryMapper {
	public static final ModelMapper modelMapper=new ModelMapper();
	public static ProductCategoryDto convertToDto(ProductCategory entity) {
	
		return modelMapper.map(entity, ProductCategoryDto.class);

	}
	
	public static ProductCategory convertToEntity(ProductCategoryDto dto) {
		return modelMapper.map(dto, ProductCategory.class);
	}
}
