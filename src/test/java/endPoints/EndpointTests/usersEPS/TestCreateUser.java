package endPoints.EndpointTests.usersEPS;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.POJOS.UserGenericErrorResponsePojo;
import payload.POJOS.UserRequestPojo;
import payload.POJOS.UserResponsePojo;
import utilities.dataprovider.DataProviders;
import utilities.dataprovider.specificFieldProviders.SpecificFieldProviders;
import utilities.requestBuilder.RequestBuilders;
import utilities.responseBuilder.ResponseBuilders;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class TestCreateUser {
    Faker faker = new Faker();
    RequestBuilders postRequestBuilder = new RequestBuilders();
    ResponseBuilders postResponseBuilder = new ResponseBuilders();
    UserRequestPojo createUserRequest = new UserRequestPojo();


    @Test
    public void createUserTest() {
        Response response = given()
                .spec(postRequestBuilder.postTestBuilder())
                .relaxedHTTPSValidation()
                .when()
                .post()
                .then()
                .spec(postResponseBuilder.postResponseBuilder())
                .extract().response();

        // Deserialize the response into UserResponsePojo
        UserResponsePojo userResponsePojo = response.as(UserResponsePojo.class);
        // Assert on UserResponsePojo's fields
        assertEquals(userResponsePojo.getCode(), 200);

        // Use RestAssured's 'body()' method to assert specific parts of the response
        response.then().body(
                //"code", equalTo(200),
                "code", instanceOf(Integer.class),
                //"code.toString()", hasLength(3),
                "type", equalTo("unknown"),
                "type", instanceOf(String.class),
                "message", instanceOf(String.class));
    }




}
