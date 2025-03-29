package com.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.ProductCategoryDto;
import com.ecom.dto.ProductDto;
import com.ecom.entity.Product;
import com.ecom.entity.ProductCategory;
import com.ecom.mapper.ProductCateroryMapper;
import com.ecom.mapper.ProductMapper;
import com.ecom.repo.ProductCategoryRepo;
import com.ecom.repo.ProductRepo;
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productrepo;
	@Autowired
	private ProductCategoryRepo categoryRepo;
	
//	private ProductCateroryMapper categoryMapper;
//	
//	private ProductMapper productMapper;
	

	@Override
	public List<ProductCategoryDto> findAllCategories() {

		return categoryRepo.findAll()
					.stream()
					.map(ProductCateroryMapper::convertToDto)
					.collect(Collectors.toList());
		
	}

	@Override
	public List<ProductDto> findProductByCategoryId(Long categoryId) {
		return productrepo.findByCategoryId(categoryId)
						  .stream()
						  .map(ProductMapper::convertToDto)
						  .collect(Collectors.toList());
		
	}

	@Override
	public ProductDto findByProductId(Long productId) {
//		Optional<Product> opt = productrepo.findById(productId);
//		return productMapper.convertToDto(opt.get());
		return productrepo.findById(productId)
					.map(ProductMapper::convertToDto)
					.orElse(null);
	}

	@Override
	public List<ProductDto> findByProductName(String productName) {
		 return productrepo.findByNameContaining(productName)
		 			.stream()
		 			.map(ProductMapper::convertToDto)
		 			.collect(Collectors.toList());   
	}

}
