package com.ecom.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ecom.dto.ProductCategoryDto;
import com.ecom.dto.ProductDto;

public interface ProductService {
//	@Query("SELECT p.category FROM Product p")
	public List<ProductCategoryDto> findAllCategories();
	public List<ProductDto> findProductByCategoryId(Long categoryId);
	public ProductDto findByProductId(Long productId);
	public List<ProductDto> findByProductName(String productName);
}
