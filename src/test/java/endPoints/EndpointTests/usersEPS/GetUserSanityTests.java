package endPoints.EndpointTests.usersEPS;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetUserSanityTests {
    Faker faker = new Faker();
    private RequestSpecification getUserSpec;
    private ResponseSpecification getUserResSpec;

    @BeforeClass
    public void requestGetBuilder() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();

        specBuilder.setBaseUri("https://petstore.swagger.io");
        specBuilder.setBasePath("/v2/user");
        specBuilder.setContentType(ContentType.JSON);
        specBuilder.addPathParam("user", "igal123"); // Replace addParam with addPathParam
        specBuilder.log(LogDetail.ALL);



        // Add response expectations using assertThat
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.log(LogDetail.ALL);
        responseSpecBuilder.expectBody("username", equalTo("igal123")); // Assertion on response body

        getUserSpec = specBuilder.build();
        getUserResSpec = responseSpecBuilder.build();

    }
    @Test
    public void createUser() {
        given().log()
                .body().and()
                .log()
                .headers()
                .and().log().ifValidationFails()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"igal123\",\n" +
                        "  \"firstName\": \"Igal\",\n" +
                        "  \"lastName\": \"Kroif\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .contentType("application/json")
                .when().post("https://petstore.swagger.io/v2/user")
                .then().statusCode(200)
                .and().log().all();
    }
    @Test
    public void myTest() {
        given()
              //  .spec(getUserSpec) // Use the RequestSpecification
                .when().get("https://petstore.swagger.io/v2/user/igal123")
                .then().log().all();
    }
    @Test
    public void getSpecificUser() {
        createUser();
        RestAssured.given()
                .spec(getUserSpec)
                .relaxedHTTPSValidation()
                .when().get("/{user}") // Use the path parameter from the base path
                .then().spec(getUserResSpec);// Use the path parameter from the base path;
    }
}


