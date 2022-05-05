package com.esgi.framework_JEE.invoice;


import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import com.esgi.framework_JEE.use_case.invoice.infrastructure.web.response.InvoiceResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import java.util.*;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class InvoiceControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup(){
        RestAssured.port = port;

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void shouldGenerateInvoiceWithUserId(){

        int user_id = 1;

        var location = InvoiceFixtures.generateInvoice(user_id)
                .then()
                .statusCode(201)
                .extract().header("Location");

        var invoiceResponse = when()
                .get(location)
                .then()
                .statusCode(302)
                .extract().body().jsonPath().getObject(".", InvoiceResponse.class);

        assertThat(invoiceResponse.getUser_id()).isEqualTo(user_id);
    }



    @Test
    public void shouldGetAllInvoice(){
        var invoicesInDatabaseBefore = when()
                .get("/api/v1/invoice")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".",  new TypeRef<List<Invoice>>() {});


        InvoiceFixtures.create()
                .then()
                .statusCode(201)
                .extract().header("Location");

        InvoiceFixtures.create()
                .then()
                .statusCode(201)
                .extract().header("Location");

        var invoicesInDatabaseAfter = when()
                .get("/api/v1/invoice")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".",  new TypeRef<List<Invoice>>() {});

        assertThat(invoicesInDatabaseAfter).hasSize(invoicesInDatabaseBefore.size() + 2);
    }


    @Test
    public void shouldDeleteInvoice(){

        var locationInvoiceCreated = InvoiceFixtures.create()
                .then()
                .statusCode(201)
                .extract().header("Location");

        when()
                .delete(locationInvoiceCreated)
                .then()
                .statusCode(200);
    }




}
