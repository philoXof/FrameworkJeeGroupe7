package com.esgi.framework_JEE.product.web.query;

import com.esgi.framework_JEE.product.domain.entities.Product;
import com.esgi.framework_JEE.product.domain.repository.ProductRepository;
import com.esgi.framework_JEE.product_category.domain.entities.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductQuery {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(int id) {
        return productRepository.getById(id);
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> getFilteredProducts(
            ProductCategory productCategory, Double price, String nutriscore, String name
    ){
        return productRepository.getProductsByProductCategoryOrPriceOrNutriscoreOrName(
                productCategory, price, nutriscore, name);
    }
}
