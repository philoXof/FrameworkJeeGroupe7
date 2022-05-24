package com.esgi.framework_JEE.use_case.product.query;

import com.esgi.framework_JEE.use_case.product.domain.entities.Product;
import com.esgi.framework_JEE.use_case.product.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductQuery {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

}
