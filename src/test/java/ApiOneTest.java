import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiOneTest {

    @Test
    public void exampleTest() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://www.googleapis.com")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.requestSpecification = requestSpec;
/*
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        RestAssured.responseSpecification = responseSpec;
*/
            given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()))
            .when()
               .get("/youtube/v3/videos")
            .then()
                .assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
