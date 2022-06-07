package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoresSteps {

    @Step("Creating store with name : {0},type:{1},address:{2},address2:{3},city:{4},state:{5},zip:{6}," +
            " lat:{7},lng:{8},hours:{9}")
    public ValidatableResponse createStore(String name, String type, String address, String address2,
                                           String city, String state, String zip, double lat, double lng, String hours) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post(Path.STORE)
                .then();
    }

    @Step("Verify store was created by verifying store name : {0}")
    public HashMap<String, Object> getStoreInfoByName(String name) {
        String p1 = "data.findAll{it.name = '";
        String p2 = "'}.get(0)";
        HashMap<String, Object> storeMap = SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_STORE)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
        return storeMap;
    }

    @Step("Verify store was updated with name :{0} ")
    public ValidatableResponse updateStore(String name, int storeId) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeId", storeId)
                .when()
                .patch(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }

    @Step("Deleting store information with storeId : {0}")
    public ValidatableResponse deleteStore(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting store information with storeId: {0}")
    public ValidatableResponse getStoreById(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }

}
