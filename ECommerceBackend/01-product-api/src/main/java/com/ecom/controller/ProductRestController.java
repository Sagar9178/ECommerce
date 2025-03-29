package com.ecom.controller;

import java.io.ObjectInputFilter.Status;
import java.util.Collections;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.ProductCategoryDto;
import com.ecom.dto.ProductDto;
import com.ecom.entity.ProductCategory;
import com.ecom.repo.ProductCategoryRepo;
import com.ecom.response.ApiResponse;
import com.ecom.service.ProductService;

@RestController
@CrossOrigin
public class ProductRestController {
	@Autowired	
	private ProductService prodservice;
	
	
	
	
	@GetMapping("/categories")
	public ResponseEntity<ApiResponse<List<ProductCategoryDto>>> productCategories(){
		List<ProductCategoryDto> allCategories = prodservice.findAllCategories();
		ApiResponse<List<ProductCategoryDto>> response=new ApiResponse<>();
		
		if(!allCategories.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Fetched Category SuccessFully");
			response.setData(allCategories);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(20);
			response.setMessage("Fetched Category Failed");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@GetMapping("/productByCategory/{categoryId}")
	public ResponseEntity<ApiResponse<List<ProductDto>>> products(@PathVariable("categoryId") Long categoryId){
		List<ProductDto> productByCategoryId = prodservice.findProductByCategoryId(categoryId);
		ApiResponse<List<ProductDto>> response=new ApiResponse<>();
		if(!productByCategoryId.isEmpty()) {
			response.setStatus(200);
			response.setMessage("fetched Product sucess..");
			response.setData(productByCategoryId);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setMessage("No Product");
			response.setData(Collections.emptyList());
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	@GetMapping("/productsByName/{name}")
	public ResponseEntity<ApiResponse<List<ProductDto>>> product(@PathVariable("name") String name){
		List<ProductDto> byProductName = prodservice.findByProductName(name);
		ApiResponse<List<ProductDto>> response=new ApiResponse<>();
		if(!byProductName.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Fetch products successfully");
			response.setData(byProductName);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setMessage("failed to Fetch products ");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@GetMapping("/productById/{productId}")
	public ResponseEntity<ApiResponse<ProductDto>> product(@PathVariable Long productId){
		ProductDto product = prodservice.findByProductId(productId);
		ApiResponse<ProductDto> response=new ApiResponse<>();
		if(product!=null) {
			response.setStatus(200);
			response.setMessage("Fetch product successfully");
			response.setData(product);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(500);
			response.setMessage("failed to Fetch product ");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	 
}
