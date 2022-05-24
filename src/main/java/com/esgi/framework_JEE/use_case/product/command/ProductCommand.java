package com.esgi.framework_JEE.use_case.product.command;

import com.esgi.framework_JEE.use_case.product.domain.entities.Product;
import com.esgi.framework_JEE.use_case.product.domain.repository.ProductRepository;
import com.esgi.framework_JEE.use_case.product.query.ProductQuery;
import com.esgi.framework_JEE.use_case.product.web.request.ProductRequest;
import com.esgi.framework_JEE.use_case.product_category.query.ProductCategoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommand {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductQuery productQuery;
    @Autowired
    ProductCategoryQuery productCategoryQuery;

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public Product create(ProductRequest productRequest){
        var product = new Product();
        product.setName(productRequest.name);
        product.setPrice(productRequest.price);
        product.setProductCategory(productCategoryQuery.getById(productRequest.productCategoryId));
        product.setNutriscore(productRequest.nutriscore);
        return saveProduct(product);
    }

    public Product updateProduct(ProductRequest productRequest, int id){
        Optional<Product> productToUpdate = productQuery.getProduct(id);
        if(productToUpdate.isPresent()){
            Product product = productToUpdate.get();
            String name = productRequest.name;
            if(name != null) {
                product.setName(name);
            }
            Double price = productRequest.price;
            if(price != null) {
                product.setPrice(price);
            }
            Integer productCategoryId = productRequest.productCategoryId;
            if(productCategoryId != null){
                product.setProductCategory(productCategoryQuery.getById(productCategoryId));
            }
            String nutriscore = productRequest.nutriscore;
            if(nutriscore != null){
                product.setNutriscore(nutriscore);
            }
            return this.saveProduct(product);
        }
        return null;
    }

    public void deleteProduct(int id) {
        Optional<Product> productToDelete = productQuery.getProduct(id);
        productToDelete.ifPresent(
            product -> productRepository.deleteById(product.getId())
        );
    }
}
