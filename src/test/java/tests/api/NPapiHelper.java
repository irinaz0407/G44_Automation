package tests.api;

import groovy.json.JsonGenerator;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;


public class NPapiHelper {
    @Step("Request getTimeIntervals with API key {0}")
    public RequestSpecification reqNPMethodGetTimeIntervals( String APIKey) {
        JSONObject jsonBody = new JSONObject();
        JSONObject methodProperties = new JSONObject();

        methodProperties.put("RecipientCityRef", "8d5a980d-391c-11dd-90d9-001a92567626");

        jsonBody.put("apiKey", APIKey);
        jsonBody.put("modelName", "Common");
        jsonBody.put("calledMethod", "getTimeIntervals");
        jsonBody.put("methodProperties", methodProperties);


    return new RequestSpecBuilder().setBaseUri("https://api.novaposhta.ua/v2.0/json/")
            .setBody(jsonBody)
            .build();
    }
    @Step("Preparing common response specification")
    public ResponseSpecification respMNMethod(){

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
//                .expectStatusCode(429)
                .build();
    }

    @Step("Request getCargoTypes with API key {0}")
    public RequestSpecification reqNPMethodgetCargoTypes( String APIKey) {
        JSONObject jsonBody = new JSONObject();

        jsonBody.put("apiKey", APIKey);
        jsonBody.put("modelName", "Common");
        jsonBody.put("calledMethod", "getCargoTypes");

        return new RequestSpecBuilder().setBaseUri("https://api.novaposhta.ua/v2.0/json/")
                .setBody(jsonBody)
                .build();
    }
}
