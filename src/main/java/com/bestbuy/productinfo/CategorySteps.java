package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.CategoriesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CategorySteps {

    @Step("This will create category id : {0}, name : {1}")
    public ValidatableResponse createCategory(String id,String name){

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setId(id);
        categoriesPojo.setName(name);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(categoriesPojo)
                .when()
                .post(EndPoints.GET_CATEGORIES)
                .then();
    }

}
