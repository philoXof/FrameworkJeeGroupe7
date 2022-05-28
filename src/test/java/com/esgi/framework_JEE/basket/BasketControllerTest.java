package com.esgi.framework_JEE.basket;


import com.esgi.framework_JEE.invoice.InvoiceFixtures;
import com.esgi.framework_JEE.use_case.basket.infrastructure.web.response.BasketResponse;
import com.esgi.framework_JEE.use_case.user.entities.User;
import com.esgi.framework_JEE.use_case.user.web.controller.UserFixture;
import com.esgi.framework_JEE.use_case.user.web.request.UserRequest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BasketControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup(){
        RestAssured.port = port;

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    //TODO : A revoir

    @Test
    public void shouldGenerateBasketWithUserId(){

        var userRequest = new UserRequest();
        userRequest.firstname = "kelyan";
        userRequest.lastname = "bervin";
        userRequest.email = "test@test.test";
        userRequest.password = "mot de passe";

        var user = UserFixture.create(userRequest)
                .then()
                .statusCode(201)
                .extract().body().jsonPath().getObject(".", User.class);


        var location = BasketFixtures.generateInvoice(user.getId())
                .then()
                .statusCode(201)
                .extract().header("Location");

        var basketResponse = when()
                .get(location)
                .then()
                .statusCode(302)
                .extract().body().jsonPath().getObject(".", BasketResponse.class);

        assertThat(basketResponse.getUserId()).isEqualTo(user.getId());

        UserFixture.deleteById(user.getId());
    }


    @Test
    public void shouldDeleteBasket(){

        var locationBasketCreated = BasketFixtures.create()
                .then()
                .statusCode(201)
                .extract().header("Location");

        when()
                .delete(locationBasketCreated)
                .then()
                .statusCode(200);

    }


    //TODO : Créer le user puis créer deux Basket,
    // le premier aura un 200 et le deuxième une 403
    @Test
    public void userCannotHaveManyBasket(){
        int user_id = 1;

        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/basket/generate/" + user_id)
                .then()
                .statusCode(403);
    }

}
