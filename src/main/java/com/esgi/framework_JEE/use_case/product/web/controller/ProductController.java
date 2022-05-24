package com.esgi.framework_JEE.use_case.product.web.controller;

import com.esgi.framework_JEE.use_case.product.command.ProductCommand;
import com.esgi.framework_JEE.use_case.product.domain.entities.Product;
import com.esgi.framework_JEE.use_case.product.query.ProductQuery;
import com.esgi.framework_JEE.use_case.product.web.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")

public class ProductController {
    @Autowired
    ProductCommand productCommand;

    @Autowired
    ProductQuery productQuery;

    /**
     * Create - Add a new product
     * @param productRequest An object employee
     * @return The product object saved
     */
    @PostMapping(value = "/create", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Product create(@RequestBody ProductRequest productRequest) {
        Product product = productCommand.create(productRequest);
        return product != null ?
                product :
                null;
    }
}
