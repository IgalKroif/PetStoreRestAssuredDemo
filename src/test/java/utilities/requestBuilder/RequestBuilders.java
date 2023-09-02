package utilities.requestBuilder;

import com.github.javafaker.Faker;
import endPoints.EndpointTests.usersEPS.TestCreateUser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import payload.POJOS.UserRequestPojo;
import utilities.dataprovider.BodyInjectors;
import utilities.dataprovider.DataProviders;
import utilities.responseBuilder.ResponseBuilders;

import static io.restassured.RestAssured.given;

public class RequestBuilders {
    public static String userName;
    RequestSpecBuilder specBuilder = new RequestSpecBuilder();
    Faker faker = new Faker();
    UserRequestPojo createUserRequest = new UserRequestPojo();

    //Builder to get specific User
    public RequestSpecification getTestBuilder() {
        specBuilder = new RequestSpecBuilder();
        specBuilder.setAuth(io.restassured.RestAssured.oauth2("1bd65eac-03e6-4605-a09c-fd069c25106a"));
        specBuilder.setBaseUri("https://petstore.swagger.io");
        specBuilder.setBasePath("/v2/user");
        specBuilder.setContentType(ContentType.JSON);
        //specBuilder.addPathParam("user", "igal123"); // Replace addParam with addPathParam
        specBuilder.log(LogDetail.ALL);
        return specBuilder.build();
    }

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

        userName = createUserRequest.getUsername();

        return createUserRequest;
    }

    public RequestSpecification postTestBuilder() {
        specBuilder = new RequestSpecBuilder();

        specBuilder.setAuth(io.restassured.RestAssured.oauth2("1bd65eac-03e6-4605-a09c-fd069c25106a"));
        specBuilder.setBaseUri("https://petstore.swagger.io");
        specBuilder.setBasePath("/v2/user");
        specBuilder.setContentType(ContentType.JSON);
        specBuilder.setBody(createUserBody());
        //specBuilder.addPathParam("user", "igal123"); // Replace addParam with addPathParam
        specBuilder.log(LogDetail.ALL);
        return specBuilder.build();
    }

//@Test(dataProvider = "differentIdFieldsProvider", dataProviderClass = DataProviders.class)
public RequestSpecification createUserFieldValidation() {
    specBuilder = new RequestSpecBuilder();

    specBuilder.setAuth(io.restassured.RestAssured.oauth2("1bd65eac-03e6-4605-a09c-fd069c25106a"));
    specBuilder.setBaseUri("https://petstore.swagger.io");
    specBuilder.setBasePath("/v2/user");
    specBuilder.setContentType(ContentType.JSON);
    //specBuilder.addPathParam("user", "igal123"); // Replace addParam with addPathParam
    specBuilder.log(LogDetail.ALL);
    return specBuilder.build();
        }
}

/*
    @Test(dataProvider = "createUserData", dataProviderClass = DataProviders.class)
    public void validateIdField(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        // Create a custom body using the provided data
        // UserRequestPojo userRequest = new UserRequestPojo();
        createUserRequest.setId(id);
        createUserRequest.setUsername(username);
        createUserRequest.setFirstName(firstName);
        createUserRequest.setLastName(lastName);
        createUserRequest.setEmail(email);
        createUserRequest.setPassword(password);
        createUserRequest.setPhone(phone);
        createUserRequest.setUserStatus(userStatus);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(createUserRequest)
                        .log().all()
                        .when()
                        .post("https://petstore.swagger.io/v2/user")  // POST request
                        .then()
                        .statusCode(200)  // Assuming a 201 Created response for successful creation
                        .log().all()
                        .extract()
                        .response();

        // Further assertions or actions on the response...
    }
*/

