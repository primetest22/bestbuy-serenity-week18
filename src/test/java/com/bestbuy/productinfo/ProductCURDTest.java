package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
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
public class ProductCURDTest extends TestBase {

    static String name = "Nippo AA Batteries" + TestUtils.getRandomValue();
    static String type = "LongLasting";
    static double price = 5.0;
    static String upc = "041333424019";
    static int shipping = 5;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Nippo";
    static String model = "MN2400B4Z";
    static String url = "www.nippo.com";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("This will create a new product")
    @Test
    public void test01() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, upc, shipping, description,
                manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId = response.log().all().extract().path("id");
        System.out.println(productId);

    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test02() {
        HashMap<String, Object> productMap = productSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
        productId = (int) productMap.get("id");
        System.out.println(productId);

    }
    @Title("Verify product was updated")
    @Test
    public void test03(){
        name = name + "_updated";

        productSteps.updateProductName(productId,name,type, price, upc, shipping, description,
                                 manufacturer, model, url, image).log().all().statusCode(200);
        System.out.println(productId);
        HashMap<String,Object> updatedName = productSteps.getProductInfoByName(name);
        Assert.assertThat(updatedName,hasValue(name));

    }
    @Title("verify product was deleted")
    @Test
    public void test04(){
        productSteps.deleteProduct(productId).statusCode(200);
        productSteps.getProductById(productId).statusCode(404);
    }
}





