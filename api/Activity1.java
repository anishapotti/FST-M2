package activities;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Order;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity1 {
    @Test(priority = 1)
    public void testPost() throws IOException {
        final String baseURI = "https://petstore.swagger.io/v2/pet";

        File file = new File("src/test/resources/input.json");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        String reqBody = new String(bytes, "UTF-8");
        Response response =
                given().contentType(ContentType.JSON).body(reqBody).
                        when().post(baseURI);
        response.then().statusCode(200);

    }

    @Test(priority = 2)
    public void testGet(){
        final String baseURI= "https://petstore.swagger.io/v2/pet/{petId}";

        Response res= given().contentType(ContentType.JSON).pathParam("petId",77233).
                when().get(baseURI);
        res.then().statusCode(200);
      //  res.then().body("[0].id", equalTo(77233));
        System.out.println(res.asPrettyString());

    }
    @Test(priority = 3)
    public void testDelete(){
        final String baseURI= "https://petstore.swagger.io/v2/pet/{petId}";
        Response res= given().contentType(ContentType.JSON).pathParam("petId",77233).
                when().delete(baseURI);
        res.then().body("code", equalTo(200));
        res.then().body("message", equalTo("77233"));
    }
}
