package endPoints.EndpointTests.usersEPS;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.POJOS.UserRequestPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class getUserTest2 {
    Faker faker = new Faker();
    public RequestSpecification getTestBuilder() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setAuth(io.restassured.RestAssured.oauth2("1bd65eac-03e6-4605-a09c-fd069c25106a"));
        specBuilder.setBaseUri("https://petstore.swagger.io");
        specBuilder.setBasePath("/v2/user");
        specBuilder.setContentType(ContentType.JSON);
        //specBuilder.addPathParam("user", "igal123"); // Replace addParam with addPathParam
        specBuilder.log(LogDetail.ALL);
        return specBuilder.build();
    }
    public ResponseSpecification getResponseBuilder() {

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        // Add response expectations using assertThat
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.log(LogDetail.ALL);
        //responseSpecBuilder.expectBody("username", equalTo("igal123")); // Assertion on response body
        return responseSpecBuilder.build();
    }


    @Test
    public void getSpecificUser() {
        TestCreateUser createAUser = new TestCreateUser();
        createAUser.createUser();
        Response response = given()
                .spec(getTestBuilder())
                .when().get("/" + createAUser.userName)
                .then().spec(getResponseBuilder()).extract().response();
    }
}
