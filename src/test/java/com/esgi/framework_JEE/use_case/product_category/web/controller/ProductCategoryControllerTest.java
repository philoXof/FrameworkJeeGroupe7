package com.esgi.framework_JEE.use_case.product_category.web.controller;

import com.esgi.framework_JEE.use_case.product_category.web.request.ProductCategoryRequest;
import com.esgi.framework_JEE.use_case.product_category.web.response.ProductCategoryResponse;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductCategoryControllerTest {
    @LocalServerPort
    int port;



    @BeforeEach
    void setup(){
        RestAssured.port = port;

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }


    @Test
    public void should_create_get_update_and_delete_product_category(){

        var productCategoryRequest = new ProductCategoryRequest();
        productCategoryRequest.name="name";

        /*
         * create
         */
        var productCategoryResponse = ProductCategoryFixtures.create(productCategoryRequest)
                .then()
                .statusCode(201)
                .extract().body().jsonPath().getObject(".", ProductCategoryResponse.class);

        var id = productCategoryResponse.getId();


        assertThat(productCategoryResponse.getName()).isEqualTo("name");


        /*
         * get by id
         */
        var getProductCategoryResponse = ProductCategoryFixtures.getById(id)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", ProductCategoryResponse.class);


        assertThat(getProductCategoryResponse.getName()).isEqualTo("name");
        assertThat(getProductCategoryResponse.getId()).isEqualTo(id);


        /*
         * update (change name)
         */
        productCategoryRequest.name = "newName";
        var updatedProductCategoryResponse = ProductCategoryFixtures.changeProductCategoryName(id,productCategoryRequest)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", ProductCategoryResponse.class);

        assertThat(updatedProductCategoryResponse.getName()).isEqualTo("newName");
        assertThat(updatedProductCategoryResponse.getId()).isEqualTo(id);


        /*
         * delete
         */
        var deleteProductCategoryResponse = ProductCategoryFixtures.deleteById(id)
                .then()
                .statusCode(200)
                .extract().body().asString();


        assertThat(deleteProductCategoryResponse).isEqualTo("ProductCategory " + id + " deleted");
    }


}
