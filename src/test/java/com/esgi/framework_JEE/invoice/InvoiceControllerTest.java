package com.esgi.framework_JEE.invoice;


import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import java.util.List;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class InvoiceControllerTest {

    @LocalServerPort
    int port;


    @BeforeEach
    void setup(){
        RestAssured.port = 8081;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void shouldGenerateInvoiceWithUserId(){

        System.out.println("-----------Je suis le port------- : " + port);
        System.out.println("-----------Je suis le port------- : " + RestAssured.port);
        var location = InvoiceFixtures.generateInvoice(1)
                .then()
                .statusCode(201)
                .extract().header("Location");


        var invoice = when()
                .get(location)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".", Invoice.class);

        assertThat(invoice.getUser().getId()).isEqualTo(1);
    }

    /*

    @Test
    public void shouldGetAllInvoice(){
        var location1 = InvoiceFixtures.create()
                .then()
                .statusCode(201)
                .extract().header("Location");

        var location2 = InvoiceFixtures.create()
                .then()
                .statusCode(201)
                .extract().header("Location");

        var invoicesInDB = when()
                .get("/api/v1/invoice")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject(".",  new TypeRef<List<Invoice>>() {});

        assertThat(invoicesInDB).hasSize(2);
    }



    @Test
    public void shouldDeleteInvoice(){

        var invoiceDelete = when()
                .delete("/api/v1/invoice/1")
                .then()
                .statusCode(200);


        var invoices = when()
                .get("/api/v1/invoice")
                .then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getObject(".", new TypeRef<List<Invoice>>() {});

        assertThat(invoices).hasSize(0);
    }

     */


}
