package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

public class ServiceSteps {

    @Step
    public ValidatableResponse createService(String name) {

        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicePojo)
                .when()
                .post(EndPoints.GET_SERVICE)
                .then();
    }
    @Step
    public HashMap<String,Object> getServiceByName(String name){

        String p1 = "data.findAll{it.name = '";
        String p2 = "'}.get(0)";
        HashMap<String,Object> serviceMap = SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_SERVICE)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
        return serviceMap;

    }
    @Step
    public ValidatableResponse updateServiceByName(String name,int serviceId){

        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .pathParam("serviceId",serviceId)
                .when()
                .patch(EndPoints.UPDATE_SERVICE_BY_ID)
                .then();
    }
    @Step("Deleting service information with serviceId : {0}")
    public ValidatableResponse deleteService(int serviceId) {
        return SerenityRest.given().log().all()
                .pathParam("serviceId", serviceId)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then();
    }

    @Step("Getting service information with serviceId: {0}")
    public ValidatableResponse getServiceById(int serviceId) {
        return SerenityRest.given().log().all()
                .pathParam("serviceId", serviceId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then();
    }


}
