package endPoints.EndpointTests.usersEPS;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import payload.POJOS.UserRequestPojo;
import payload.POJOS.UserResponsePojo;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class TestCreateUser {
    public String userName;
    Faker faker = new Faker();
    UserRequestPojo createUserRequest = new UserRequestPojo();

    public Object createUserBody() {
        createUserRequest = new UserRequestPojo();
        createUserRequest.setId(faker.idNumber().hashCode());
        createUserRequest.setUsername(faker.name().username());
        createUserRequest.setFirstName(faker.name().firstName());
        createUserRequest.setLastName(faker.name().lastName());
        createUserRequest.setEmail(faker.internet().emailAddress());
        createUserRequest.setPassword(faker.internet().password());
        createUserRequest.setPhone(faker.phoneNumber().phoneNumber());
        createUserRequest.setUserStatus(faker.number().numberBetween(1, 9));

        this.userName = createUserRequest.getUsername();

        return createUserRequest;
    }

    public RequestSpecification postTestBuilder() {
        //faker = new Faker();
        //createUserRequest = new UserRequestPojo();
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setAuth(io.restassured.RestAssured.oauth2("1bd65eac-03e6-4605-a09c-fd069c25106a"));
        specBuilder.setBaseUri("https://petstore.swagger.io");
        specBuilder.setBasePath("/v2/user");
        specBuilder.setContentType(ContentType.JSON);
        specBuilder.setBody(createUserBody());
        //specBuilder.addPathParam("user", "igal123"); // Replace addParam with addPathParam
        specBuilder.log(LogDetail.ALL);
        return specBuilder.build();
    }

    public ResponseSpecification postResponseBuilder() {

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        // Add response expectations using assertThat
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.log(LogDetail.ALL);
        //responseSpecBuilder.expectBody("username", equalTo("igal123")); // Assertion on response body
        return responseSpecBuilder.build();
    }

    @Test
    public void createUser() {
        createUserBody(); // Generate user data first

        Response response = given()
                .spec(postTestBuilder())
                .relaxedHTTPSValidation()
                .when()
                .post()
                .then()
                .spec(postResponseBuilder())
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
