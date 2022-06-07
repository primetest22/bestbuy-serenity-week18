package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresCURDTest extends TestBase {

    static String name = "Hamleys";
    static String type = "Toys";
    static String address = "13513 Ridgedale Dr";
    static String address2 = "";
    static  String city = "Mumbai";
    static String state = "MH";
    static String zip = "55305";
    static double lat = 44.969658;
    static double lng = -93.449539;
    static  String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static  int storeId;

    @Steps StoresSteps storesSteps;

    @Title(" This will create new store ")
    @Test
    public void test001() {
        ValidatableResponse response = storesSteps.createStore(name,type,address,address2,city,
                state,zip,lat,lng,hours);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002(){
        HashMap<String,Object> storeMap = storesSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap,hasValue(name));
        storeId = (int) storeMap.get("id");
    }
    @Title("Verify store was updated")
    @Test
    public void test003(){
        name = name + "_updated";
        storesSteps.updateStore(name,storeId).log().all().statusCode(200);
        System.out.println(storeId);
        HashMap<String,Object> updatedName = storesSteps.getStoreInfoByName(name);
        Assert.assertThat(updatedName,hasValue(name));
    }
    @Title("Delete the store and verify if the store is deleted")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.getStoreById(storeId).statusCode(404);
    }

}
