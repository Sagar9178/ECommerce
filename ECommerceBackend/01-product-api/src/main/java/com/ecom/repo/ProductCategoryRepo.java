package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.ProductCategory;
@Repository
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long>{

}
