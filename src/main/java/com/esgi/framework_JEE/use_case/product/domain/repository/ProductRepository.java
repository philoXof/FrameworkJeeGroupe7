package com.esgi.framework_JEE.use_case.product.domain.repository;

import com.esgi.framework_JEE.use_case.product.domain.entities.Product;
import com.esgi.framework_JEE.use_case.product_category.domain.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
