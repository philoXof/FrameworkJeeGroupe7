package com.esgi.framework_JEE.use_case.slot.web.controller;

import com.esgi.framework_JEE.use_case.slot.domain.entities.Slot;
import com.esgi.framework_JEE.use_case.slot.web.request.SlotRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SlotFixtures {
    public static Response create(SlotRequest slotRequest){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(slotRequest)
                .post("/slot/create");
    }

    public static Response getByStart(SlotRequest slotRequest){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(slotRequest)
                .get("/slot/start");
    }

    public static Response getById(int slotId){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/slot/" + slotId);
    }

    public static Response getAll(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/slot/");
    }


    public static Response changeSlotStart(int slotId, SlotRequest slotRequest){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(slotRequest)
                .patch("/slot/start/" + slotId);
    }

    public static Response changeSlotEnd(int slotId, SlotRequest slotRequest){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(slotRequest)
                .patch("/slot/end/" + slotId);
    }


    public static Response deleteById(int slotId){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/slot/" + slotId);
    }
}
