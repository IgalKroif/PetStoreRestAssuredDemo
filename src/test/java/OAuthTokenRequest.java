import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Base64;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Base64;

public class OAuthTokenRequest {
    public static String accessToken;

    @BeforeClass
    public void oauthTest() {
        // Set the client credentials
        String clientId = "test";
        String clientSecret = "abc123";

        // Base64 encode the client credentials
        String encodedCredentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        // Set the request body
        String requestBody = "grant_type=client_credentials&scope=read+write+read:pets+write:pets";

        // Send the request to the specified URL
        String response = RestAssured.given()
                .header("Authorization", "Basic " + encodedCredentials)
                .contentType(ContentType.URLENC)
                .body(requestBody)
                .post("https://petstore.swagger.io/oauth/token")
                .then().extract().response().body().asString();

        // Extract the access token from the response and assign it to the class variable
        accessToken = extractAccessToken(response);

        // Handle the response
    }

    @Test
    public void testUsingAccessToken() {
        // You can access the access token from the class variable in this method
        System.out.println("Access Token: " + accessToken);

        // Use the 'accessToken' as needed in this test method
    }

    // Utility method to extract the access token from the response
    private String extractAccessToken(String response) {
        return response.split("\"access_token\":\"")[1].split("\"")[0];
    }

@Test
public void test() {
    System.out.println(accessToken);
    }
}
