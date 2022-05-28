package com.esgi.framework_JEE.use_case.user.web.controller;

import com.esgi.framework_JEE.use_case.user.web.request.UserRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserFixture {
    public static Response create(UserRequest userRequest){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(userRequest)
                .post("/user/create");
    }

    public static Response getById(int userId){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/user/" + userId);
    }


    public static Response changeUserName(int userId, UserRequest userRequest){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(userRequest)
                .patch("/user/" + userId);
    }


    public static Response deleteById(int userId){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/user/" + userId);
    }

    public static Response getAll(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/user/");
    }
    
}
