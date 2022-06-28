package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity2 {
    final static String ROOT_URI = "https://petstore.swagger.io/v2/user";

    @Test(priority = 1)
    public void addNewUser() throws IOException {
        File file = new File("src/test/resources/input2.json");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        String reqBody = new String(bytes, "UTF-8");
        Response response =
                given().contentType(ContentType.JSON).body(reqBody).
                        when().post(ROOT_URI);
        response.then().statusCode(200);
        response.then().body("message",equalTo("9901"));

    }

    @Test(priority = 2)
    public void getUserDetails(){
        final String baseURI= "https://petstore.swagger.io/v2/user/{username}";

        Response res= given().contentType(ContentType.JSON).pathParam("username","justinc").
                when().get(baseURI);
        res.then().statusCode(200);
        res.then().body("id",equalTo(9901));
        res.then().body("firstName",equalTo("Justin"));
        res.then().body("lastName",equalTo("Case"));
        System.out.println(res.asPrettyString());


    }
    @Test(priority = 3)
    public void deleteUser(){
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .pathParam("username", "justinc") // Add path parameter
                        .when().delete(ROOT_URI + "/{username}"); // Send POST request

        // Assertion
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("justinc"));
    }
}
