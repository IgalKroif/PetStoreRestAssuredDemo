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
import Routes.users.getEP.getUsers;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;

import  io.restassured.assertion.*;
public class getUserTest {
    Faker faker = new Faker();
    private RequestSpecification getUserSpec;
    private ResponseSpecification getUserResSpec;

    @BeforeClass
    public void requestGetBuilder() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri("https://petstore.swagger.io");
        specBuilder.setBasePath("/v2/user");
        specBuilder.setContentType(ContentType.JSON);
        specBuilder.addPathParam("user", "igal123"); // Replace addParam with addPathParam
        specBuilder.log(LogDetail.ALL);
        getUserSpec = specBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        // Add response expectations using assertThat
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.log(LogDetail.ALL);
        responseSpecBuilder.expectBody("username", equalTo("igal123")); // Assertion on response body

        getUserResSpec = responseSpecBuilder.build();

    }


    @Test
    public void createUser() {
        given().
                body("{\n" +
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
                .spec(getUserSpec) // Use the RequestSpecification
                .when().get("https://petstore.swagger.io/v2/user/igal123")
                .then().log().all();
    }

    @Test
    public void getSpecificUser() {
        RestAssured.given()
                .spec(getUserSpec)
                .relaxedHTTPSValidation()
                .when().get("/{user}") // Use the path parameter from the base path
                .then().spec(getUserResSpec);// Use the path parameter from the base path;
    }

}
