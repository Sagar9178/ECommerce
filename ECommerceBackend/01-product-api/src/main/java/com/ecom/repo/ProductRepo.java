package com.ecom.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.dto.ProductCategoryDto;
import com.ecom.entity.Product;
import com.ecom.entity.ProductCategory;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	


	public List<Product> findByCategoryId(Long categoryId);

	public List<Product> findByNameContaining(String name);

//	public List<ProductCategory> findAllCategories();

}
