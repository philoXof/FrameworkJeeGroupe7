package com.esgi.framework_JEE.use_case.product_category.web.controller;

import com.esgi.framework_JEE.use_case.product_category.web.request.ProductCategoryRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductCategoryFixtures {
    public static Response create(ProductCategoryRequest productCategoryRequest){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(productCategoryRequest)
                .post("/product_category/create");
    }

    public static Response getById(int product_categoryId){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/product_category/" + product_categoryId);
    }


    public static Response changeProductCategoryName(int product_categoryId, ProductCategoryRequest productCategoryRequest){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(productCategoryRequest)
                .patch("/product_category/" + product_categoryId);
    }


    public static Response deleteById(int product_categoryId){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/product_category/" + product_categoryId);
    }

    public static Response getAll(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/product_category/");
    }

}
