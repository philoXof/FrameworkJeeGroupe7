package com.esgi.framework_JEE.use_case.Product.domain.repository;

import com.esgi.framework_JEE.use_case.Product.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(int productId);

    List<Product> findAll();

    List<Product> findProductsByCategoryId(int categoryId);

    Product findProductByName();
}
