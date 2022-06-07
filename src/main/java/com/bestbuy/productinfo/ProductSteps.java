package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {

    @Step
    public ValidatableResponse createProduct(String name, String type, double price, String upc,
                                             int shipping, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = new ProductPojo();

        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                // .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post(EndPoints.CREATE_PRODUCT)
                .then();
    }

    @Step("Getting the product information with name: {0}")
    public HashMap<String, Object> getProductInfoByName(String name) {
        String p1 = "data.findAll{it.name ='";
        String p2 = "'}.get(0)";
        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .when()
                .get(EndPoints.CREATE_PRODUCT)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);

        return productMap;
    }

    @Step("Verify product is updated")
    public ValidatableResponse updateProductName(int productId, String name, String type, double price, String upc,
                                                 int shipping, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productId", productId)
                .body(productPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step("verify product is deleted")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting product information with productId: {0}")
    public ValidatableResponse getProductById(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }


}
