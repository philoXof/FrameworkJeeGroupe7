package com.esgi.framework_JEE.use_case.product_category.command;

import com.esgi.framework_JEE.use_case.product_category.domain.entities.entities.ProductCategory;
import com.esgi.framework_JEE.use_case.product_category.domain.repository.ProductCategoryRepository;
import com.esgi.framework_JEE.use_case.product_category.validation.ProductCategoryValidation;
import com.esgi.framework_JEE.use_case.product_category.web.request.ProductCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryCommand {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    ProductCategoryValidation productCategoryValidation = new ProductCategoryValidation();

    public ProductCategory create(ProductCategoryRequest productCategoryRequest){
        var productCategory = new ProductCategory();
        productCategory.setName(productCategoryRequest.name);
        if(productCategoryValidation.isValid(productCategory))
            return productCategoryRepository.save(productCategory);
        return null;
    }

    public ProductCategory changeName(ProductCategoryRequest productCategoryRequest, int id){
        Optional<ProductCategory> dbProductCategory = Optional.ofNullable(productCategoryRepository.findById(id));
        if(dbProductCategory.isPresent()){
            dbProductCategory.get().setName(productCategoryRequest.name);
            return productCategoryRepository.save(dbProductCategory.get());
        }
        return null;
    }

    public void delete(int productCategoryId){
        Optional<ProductCategory> dbProductCategory = Optional.ofNullable(productCategoryRepository.findById(productCategoryId));
        dbProductCategory.ifPresent(productCategory -> delete(productCategoryId));
    }
}
