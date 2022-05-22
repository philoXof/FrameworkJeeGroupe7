package com.esgi.framework_JEE.basket;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasketFixtures {

    public static Response create(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/basket");
    }

    public static Response generateInvoice(int user_id){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/basket/generate/" + user_id);
    }
}
