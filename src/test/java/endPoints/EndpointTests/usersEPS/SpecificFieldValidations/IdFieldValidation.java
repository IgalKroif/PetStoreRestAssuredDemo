package endPoints.EndpointTests.usersEPS.SpecificFieldValidations;

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
import static org.hamcrest.Matchers.equalTo;

public class IdFieldValidation {
    Faker faker = new Faker();
    RequestBuilders postRequestBuilder = new RequestBuilders();
    ResponseBuilders postResponseBuilder = new ResponseBuilders();
    UserRequestPojo createUserRequest = new UserRequestPojo();

    /**
     * Validates the ID field by creating a user with provided data and sending a POST request to the specified endpoint.
     * Uses data from the "differentIdFieldsProvider" to test different scenarios.
     *
     * @param id The unique identifier for the user.
     * @param username The username for the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email address of the user.
     * @param password The password for the user's account.
     * @param phone The phone number associated with the user.
     * @param userStatus The status of the user.
     *
     * @implNote This method uses Faker to generate some of the user's details and RestAssured to send the HTTP POST request.
     *           It assumes a 200 OK response indicates successful user creation.
     */
    @Test(dataProvider = "differentIdFieldsProvider", dataProviderClass = DataProviders.class)
    public void validateIdField(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        // Create a custom body using the provided data
        // UserRequestPojo userRequest = new UserRequestPojo();
        createUserRequest.setId(id);
        createUserRequest.setUsername(faker.name().username());
        createUserRequest.setFirstName(faker.name().firstName());
        createUserRequest.setLastName(faker.name().lastName());
        createUserRequest.setEmail(faker.internet().emailAddress());
        createUserRequest.setPassword(faker.internet().password());
        createUserRequest.setPhone(faker.phoneNumber().phoneNumber());
        createUserRequest.setUserStatus(faker.number().numberBetween(1, 9));

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

    @Test(dataProvider = "differentIdFields", dataProviderClass = SpecificFieldProviders.class)
    public void validateIdFieldTest(Object id) {
        createUserRequest = new UserRequestPojo();
        createUserRequest.setId(id);
        createUserRequest.setUsername(faker.name().username());
        createUserRequest.setFirstName(faker.name().firstName());
        createUserRequest.setLastName(faker.name().lastName());
        createUserRequest.setEmail(faker.internet().emailAddress());
        createUserRequest.setPassword(faker.internet().password());
        createUserRequest.setPhone(faker.phoneNumber().phoneNumber());
        createUserRequest.setUserStatus(faker.number().numberBetween(1, 9));

        Response response =
                given()
                        .spec(postRequestBuilder.createUserFieldValidation())
                        .body(createUserRequest)  // Set the request body
                        .when()
                        .post(); // Make the POST request
        int statusCode = response.statusCode(); // Extract status code for switch and assertions

        switch (statusCode) {
            case 200:
                UserResponsePojo userResponse = response.then()
                        .spec(postResponseBuilder.postResponseBuilder())
                        .extract()
                        .as(UserResponsePojo.class);

                // Validate the 'code' field from the response
                Assert.assertEquals(statusCode, 200, "Expected 200 got: " + response.statusCode());
                Assert.assertEquals(response.header("Content-Type"), "application/json");
                response.then().log().all();
                break;

            case 500:
                UserGenericErrorResponsePojo errorResponse = response.then()
                        .extract()
                        .as(UserGenericErrorResponsePojo.class);

                // Log the response body
                response.then().log().body();
                response.then().body("message", equalTo("something bad happened"),
                        "type", equalTo("unknown"));
                Assert.assertEquals(statusCode, 500, "Expected 500 got: " + response.statusCode());
                Assert.assertEquals(response.header("Content-Type"), "application/json");
                break;
        }
    }

}