package tests.api;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PrivatApiTest extends PrivatBaseApiTest {


    @Test
    public void getPrivateExchangeRateArchive() {
        given()
                .spec(reqSpec)
                .queryParam("coursid", "5").
        when()
                .log()
                .all(true)
                .get("/pubinfo").
        then()
                .spec(resSpec)
                .log()
                .all(true);

    }

}
