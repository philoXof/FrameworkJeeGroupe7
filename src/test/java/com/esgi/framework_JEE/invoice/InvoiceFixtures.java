package com.esgi.framework_JEE.invoice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class InvoiceFixtures {

    public static Response create(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/invoice");
    }

    public static Response generateInvoice(int user_id){
        return given()
                .contentType(ContentType.JSON)
                .queryParam(String.valueOf(user_id))
                .when()
                .post("/api/v1/invoice/generate/");
    }

}
