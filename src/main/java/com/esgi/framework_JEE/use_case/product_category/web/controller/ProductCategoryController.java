package com.esgi.framework_JEE.use_case.product_category.web.controller;

import com.esgi.framework_JEE.use_case.product_category.command.ProductCategoryCommand;
import com.esgi.framework_JEE.use_case.product_category.domain.entities.entities.ProductCategory;
import com.esgi.framework_JEE.use_case.product_category.query.ProductCategoryQuery;
import com.esgi.framework_JEE.use_case.product_category.web.request.ProductCategoryRequest;
import com.esgi.framework_JEE.use_case.product_category.web.response.ProductCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product_category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryCommand productCategoryCommand;

    @Autowired
    ProductCategoryQuery productCategoryQuery;


    public ProductCategoryController(ProductCategoryCommand productCategoryCommand, ProductCategoryQuery productCategoryQuery) {
        this.productCategoryCommand = productCategoryCommand;
        this.productCategoryQuery = productCategoryQuery;
    }

    //todo test all
    @PostMapping("/create")
    public ResponseEntity<ProductCategoryResponse> create(@RequestBody ProductCategoryRequest productCategoryRequest){
        var productCategory = productCategoryCommand.create(productCategoryRequest);
        if(productCategory == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(productCategoryToProductCategoryResponse(productCategory),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductCategoryResponse>> getAll(){
        return new ResponseEntity<>(
                listProductCategoryToListProductCategoryResponse(
                        productCategoryQuery.getAll()
                ),
                HttpStatus.OK);
    }

    @GetMapping("/{productCategoryId}")
    public ResponseEntity<ProductCategoryResponse> getProductCategoryById(@PathVariable int productCategoryId){
        var productCategory = productCategoryQuery.getById(productCategoryId);
        if(productCategory == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(
                productCategoryToProductCategoryResponse(productCategory),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{productCategoryId}")
    public ResponseEntity<ProductCategoryResponse> changeName(@PathVariable int productCategoryId,@RequestBody ProductCategoryRequest productCategoryRequest){
        var productCategory = productCategoryCommand.changeName(productCategoryRequest,productCategoryId);
        if(productCategory == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(productCategoryToProductCategoryResponse(productCategory), HttpStatus.OK);
    }


    @DeleteMapping("/{productCategoryId}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable int productCategoryId){
        var productCategory = productCategoryQuery.getById(productCategoryId);
        if(productCategory == null)
            return new ResponseEntity<>(
                    "ProductCategory " + productCategoryId + " not exist",
                    HttpStatus.BAD_REQUEST
            );
        productCategoryCommand.delete(productCategoryId);
        return new ResponseEntity<>(
                "ProductCategory " + productCategoryId + " deleted",
                HttpStatus.BAD_REQUEST
        );
    }







    private ProductCategoryResponse productCategoryToProductCategoryResponse(ProductCategory productCategory){
        return new ProductCategoryResponse()
                .setId(productCategory.getId())
                .setName(productCategory.getName());
    }

    private List<ProductCategoryResponse> listProductCategoryToListProductCategoryResponse(List<ProductCategory> productCategories){
        List<ProductCategoryResponse> productCategoryResponses = new ArrayList<>();
        productCategories.forEach(productCategory -> productCategoryResponses.add(
                productCategoryToProductCategoryResponse(productCategory))
        );
        return productCategoryResponses;
    }
}
