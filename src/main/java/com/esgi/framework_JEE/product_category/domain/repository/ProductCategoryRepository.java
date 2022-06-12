package com.esgi.framework_JEE.product_category.domain.repository;

import com.esgi.framework_JEE.product_category.domain.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    ProductCategory findById(int id);
}
