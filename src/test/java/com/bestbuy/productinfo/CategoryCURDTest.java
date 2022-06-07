package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CategoryCURDTest extends TestBase {

    static String id = "abcat100100";
    static String name = "Apple";
    static int categoryId;

    @Steps
    CategorySteps categorySteps;

    @Title("This will create category ")
    @Test
    public void test1(){
        ValidatableResponse response = categorySteps.createCategory(id,name);
        response.log().all().statusCode(201);
    }

}
