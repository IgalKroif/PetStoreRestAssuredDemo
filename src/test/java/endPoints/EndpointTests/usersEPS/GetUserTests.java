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
import utilities.dataprovider.DataProviders;
import utilities.requestBuilder.RequestBuilders;
import utilities.responseBuilder.ResponseBuilders;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserTests {
    Faker faker = new Faker();
    TestCreateUser createAUser = new TestCreateUser();
    RequestBuilders createUserBuild = new RequestBuilders();
    ResponseBuilders createUserResponse = new ResponseBuilders();
    @Test
    public void getSpecificUserTest() {
        createAUser.createUserTest();
        Response response = given()
                .spec(createUserBuild.getTestBuilder())
                .when().get("/" + RequestBuilders.userName)
                .then().spec(createUserResponse.getResponseBuilder()).extract().response();

        response.then()
                .body("id", instanceOf(Integer.class),
                        "username", instanceOf(String.class),
                        "firstName", instanceOf(String.class),
                        "lastName", instanceOf(String.class),
                        "email", instanceOf(String.class),
                        "password", instanceOf(String.class),
                        "phone", instanceOf(String.class),
                        "userStatus", instanceOf(Integer.class)
                );
    }
}