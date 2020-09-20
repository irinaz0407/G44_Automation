package tests.api;


import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static java.lang.System.getProperty;


public class NPApiTest {

    protected Logger log = LogManager.getLogger(this.getClass().getName());
    private static NPapiHelper apiHelper;
    protected RequestSpecification reqSpec;
    protected ResponseSpecification resSpec;
    protected static String moduleList = "getTimeIntervals,getCargoTypes,getBackwardDeliveryCargoTypes";
    protected static String apiKey = "466a2f3c44d6d024fb45db5e893a039c";

@Step("Initialization")
@Before
    public void initReq()
{
    this.apiHelper = new NPapiHelper();
    this.resSpec = apiHelper.respMNMethod();
}

@Description("Test of getTimeIntervals")
@Step("Schema validation")
@Severity(SeverityLevel.BLOCKER)
@Test
public void callgetTimeIntervals(){
    this.reqSpec = apiHelper.reqNPMethodGetTimeIntervals(apiKey);
    given()
            .spec(reqSpec)
    .when()
            .log()
            .all(true)
            .put()
    .then()
            .spec(this.resSpec)
            .log()
            .all(true)
            .body(matchesJsonSchema(
                    new File(getProperty("user.dir")
                            + "/src/test/resources/getTimeInterval-schema.json")));
}

    @Description("Test of getCargoTypes")
    @Step("Schema validation")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void callgetCargoTypes(){
        this.reqSpec = apiHelper.reqNPMethodgetCargoTypes(apiKey);
        given()
                .spec(reqSpec)
                .when()
                .log()
                .all(true)
                .put()
                .then()
                .spec(this.resSpec)
                .log()
                .all(true)
                .body(matchesJsonSchema(
                        new File(getProperty("user.dir")
                                + "/src/test/resources/getCargoTypes-schema.json")));
    }
}



