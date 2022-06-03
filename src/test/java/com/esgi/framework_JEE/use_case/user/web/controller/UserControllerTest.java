package com.esgi.framework_JEE.use_case.user.web.controller;

import com.esgi.framework_JEE.use_case.user.web.request.UserRequest;
import com.esgi.framework_JEE.use_case.user.web.response.UserResponse;
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
class UserControllerTest {
    @LocalServerPort
    int port;

    public UserRequest user1 = new UserRequest();
    public UserRequest user2 = new UserRequest();
    public UserRequest user3 = new UserRequest();

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        user1.email= "ljehanno@myges.fr";
        user1.firstname = "lucas";
        user1.lastname = "jehanno";
        user1.password = "securite !";

        user2.email = "kbervin@myges.fr";
        user2.firstname = "kelyan";
        user2.lastname = "bervin";
        user2.password = "j aime les pates";

        user3.email = "hbah@myges.fr";
        user3.firstname = "halimatou";
        user3.lastname = "bah";
        user3.password = "bahbibel";
    }

    @Test
    public void should_create_get_update_and_delete_user(){

        /*
         * create
         */
        var userResponse = UserFixture.create(user1)
                .then()
                .statusCode(201)
                .extract().body().jsonPath().getObject(".", UserResponse.class);

        var id = userResponse.getId();


        assertThat(userResponse.getEmail()).isEqualTo(user1.email);
        assertThat(userResponse.getFirstname()).isEqualTo(user1.firstname);
        assertThat(userResponse.getLastname()).isEqualTo(user1.lastname);


        /*
         * get by id
         */
        var getUserResponse = UserFixture.getById(id)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", UserResponse.class);


        assertThat(userResponse.getEmail()).isEqualTo(user1.email);
        assertThat(userResponse.getFirstname()).isEqualTo(user1.firstname);
        assertThat(userResponse.getLastname()).isEqualTo(user1.lastname);

        assertThat(getUserResponse.getId()).isEqualTo(id);


        /*
         * update (change lastname)
         */
        user1.lastname = "";
        UserFixture.changeLastname(id,user1)
                .then()
                .statusCode(400);

        user1.lastname = "JEHANNO";
        var updatedUserResponse = UserFixture.changeLastname(id,user1)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", UserResponse.class);

        assertThat(updatedUserResponse.getEmail()).isEqualTo(user1.email);
        assertThat(updatedUserResponse.getFirstname()).isEqualTo(user1.firstname);
        assertThat(updatedUserResponse.getLastname()).isEqualTo(user1.lastname);
        assertThat(updatedUserResponse.getId()).isEqualTo(id);

        /*
         * update (change firstname)
         */
        user1.firstname = "";
        UserFixture.changeFirstname(id,user1)
                .then()
                .statusCode(400);

        user1.firstname = "LUCAS";
        var updatedFirstnameUserResponse = UserFixture.changeFirstname(id,user1)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", UserResponse.class);

        assertThat(updatedFirstnameUserResponse.getEmail()).isEqualTo(user1.email);
        assertThat(updatedFirstnameUserResponse.getFirstname()).isEqualTo(user1.firstname);
        assertThat(updatedFirstnameUserResponse.getLastname()).isEqualTo(user1.lastname);
        assertThat(updatedFirstnameUserResponse.getId()).isEqualTo(id);

        /*
         * update (change password)
         */
        user1.password = "";
        UserFixture.changePassword(id,user1)
                .then()
                .statusCode(400);

        user1.password = "coucou c est mon nouveau mdp";
        var updatedPasswordUserResponse = UserFixture.changePassword(id,user1)
                .then()
                .statusCode(200);


        /*
         * CHANGE EMAIL
         */
        user1.email = "jehanno.lucas@gmail.com";
        var updatedEmailUserResponse = UserFixture.changeEmail(id,user1)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", UserResponse.class);

        assertThat(updatedEmailUserResponse.getEmail()).isEqualTo(user1.email);
        assertThat(updatedEmailUserResponse.getFirstname()).isEqualTo(user1.firstname);
        assertThat(updatedEmailUserResponse.getLastname()).isEqualTo(user1.lastname);
        assertThat(updatedEmailUserResponse.getId()).isEqualTo(id);

        /*
         * delete
         */
        var deleteUserResponse = UserFixture.deleteById(id)
                .then()
                .statusCode(202)
                .extract().body().asString();


        assertThat(deleteUserResponse).isEqualTo("User " + id + " deleted");

        deleteUserResponse = UserFixture.deleteById(id)
                .then()
                .statusCode(400)
                .extract().body().asString();


        assertThat(deleteUserResponse).isEqualTo("User " + id + " not exist");
    }

    @Test
    public void should_return_bad_request() {

        user1.email = "";

        var response = UserFixture.create(user1)
                .then()
                .statusCode(400)
                .extract().body().asString();
        assertThat(response).isEqualTo("");

        /*
         * get by id
         */
        var response2 = UserFixture.getById(0)
                .then()
                .statusCode(400)
                .extract().body().asString();

        assertThat(response2).isEqualTo("");

        var response3 = UserFixture.changeEmail(0,new UserRequest())
                .then()
                .statusCode(400)
                .extract().body().asString();

        assertThat(response3).isEqualTo("");

    }

    @Test
    public void should_test_login_route(){
        UserFixture.create(user3)
                .then()
                .statusCode(201)
                .extract().body().jsonPath().getObject(".", UserResponse.class);

        UserFixture.login(user3)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", UserResponse.class);
        user3.email = "email@non-in.bdd";
        UserFixture.login(user3)
                .then()
                .statusCode(400)
                .extract().body().asString();
    }

    @Test
    public void should_get_all() {
        user1.email= "ah@myges.fr";
        user1.firstname = "fsdsd";
        user1.lastname = "sdf";
        user1.password = "sdqfdqsf !";
        UserFixture.create(user1)
                .then()
                .statusCode(201)
                .extract().body().jsonPath().getObject(".", UserResponse.class);

        var slotResponse = UserFixture.getAll()
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", UserResponse.class);

        assertThat(slotResponse).isNotEmpty();
    }


}