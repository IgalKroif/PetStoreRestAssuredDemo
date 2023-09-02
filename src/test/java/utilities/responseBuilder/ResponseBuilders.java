package utilities.responseBuilder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseBuilders {

    public ResponseSpecification getResponseBuilder() {

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        // Add response expectations using assertThat
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.log(LogDetail.ALL);
        //responseSpecBuilder.expectBody("username", equalTo("igal123")); // Assertion on response body
        return responseSpecBuilder.build();
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
}
