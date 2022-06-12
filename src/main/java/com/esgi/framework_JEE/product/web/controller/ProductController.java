package com.esgi.framework_JEE.product.web.controller;


import com.esgi.framework_JEE.product.web.request.ProductRequest;
import com.esgi.framework_JEE.product.web.command.ProductCommand;
import com.esgi.framework_JEE.product.domain.entities.Product;
import com.esgi.framework_JEE.product.web.query.ProductQuery;
import com.esgi.framework_JEE.product_category.query.ProductCategoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    ProductCommand productCommand;

    @Autowired
    ProductQuery productQuery;

    @Autowired
    ProductCategoryQuery productCategoryQuery;

    /**
     * Create - Add a new product
     * @param productRequest An object product
     * @return The product object saved
     */
    @PostMapping(value = "/create", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Product create(@RequestBody ProductRequest productRequest) {
        return productCommand.create(productRequest);
    }

    /**
     * Read - Get one product
     * @param id The id of the product
     * @return An Product object full filled
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable("id") int id) {
        Product product = productQuery.getProduct(id);
        return product;
    }


    /**
     * Read - Get all products
     * @return - An Iterable object of Product full filled
     */
    @GetMapping("/")
    @ResponseBody
    public Iterable<Product> getAllproducts(
            @RequestBody ProductRequest productRequest)
    {
        ProductCategory productCategory = productRequest.productCategoryId != null ? productCategoryQuery.getById(productRequest.productCategoryId) : null;
        System.out.println("Here i am " + productRequest.name);
        return productQuery.getFilteredProducts(productCategory, productRequest.price, productRequest.nutriscore, productRequest.name);

    }
    /*
    TODO Later add the filter version
     */

    /**
     * Update - Update an existing product
     * @param productRequest An object product
     * @return The product object updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody ProductRequest productRequest) {
        if(productQuery.getProduct(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productCommand.updateProduct(productRequest, id));
    }

    /**
     * Delete - Delete an product
     * @param id - The id of the product to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        Product product = productQuery.getProduct(id);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }
        productCommand.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
