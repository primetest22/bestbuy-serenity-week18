package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ServiceCURDTest extends TestBase {

    static String name = "Jaguar Land Rover";
    static int serviceId;

    @Steps ServiceSteps serviceSteps;

    @Test
    public void test0001(){
        ValidatableResponse response = serviceSteps.createService(name);
        response.log().all().statusCode(201);
    }
    @Test
    public void test0002(){
        HashMap<String,Object> serviceMap = serviceSteps.getServiceByName(name);
        Assert.assertThat(serviceMap, hasValue(name));
        System.out.println("test1 " +serviceMap);
        System.out.println("test2 "+hasValue(name));
        serviceId = (int) serviceMap.get("id");
    }
    @Test
    public void test0003(){
        name = name + "_updated";
         serviceSteps.updateServiceByName(name,serviceId).log().all().statusCode(200);
        HashMap<String,Object> updatedName = serviceSteps.getServiceByName(name);
        Assert.assertThat(updatedName,hasValue(name));
    }
    @Test
    public void test04(){
        serviceSteps.deleteService(serviceId).statusCode(200);
        serviceSteps.getServiceById(serviceId).statusCode(404);
    }


}
