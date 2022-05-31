package com.esgi.framework_JEE.basket;


import com.esgi.framework_JEE.TestFixtures;
import com.esgi.framework_JEE.use_case.basket.infrastructure.web.response.BasketResponse;
import com.esgi.framework_JEE.use_case.user.entities.User;
import com.esgi.framework_JEE.use_case.user.web.controller.UserFixture;
import com.esgi.framework_JEE.use_case.user.web.request.UserRequest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;



import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BasketControllerTest {

    @LocalServerPort
    int port;

    UserRequest userRequest = new UserRequest();

    @BeforeEach
    void setup(){
        RestAssured.port = port;

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        userRequest.firstname = "kelyan";
        userRequest.lastname = "bervin";
        userRequest.email = TestFixtures.randomEmail();
        userRequest.password = "mot de passe";
    }

    @Test
    public void shouldGenerateBasketWithUserId(){

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


        userRequest.email = TestFixtures.randomEmail();
        userRequest.password = "mot de passe";

        var locationBasketCreated = BasketFixtures.create()
                .then()
                .statusCode(201)
                .extract().header("Location");

        when()
                .delete(locationBasketCreated)
                .then()
                .statusCode(200);

    }

    @Test
    public void userCannotHaveManyBasket(){

        userRequest.email = TestFixtures.randomEmail();

        var user = UserFixture.create(userRequest)
                .then()
                .statusCode(201)
                .extract().body().jsonPath().getObject(".", User.class);


        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/basket/generate/" + user.getId())
                .then()
                .statusCode(201);

        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/basket/generate/" + user.getId())
                .then()
                .statusCode(403);
    }





}
