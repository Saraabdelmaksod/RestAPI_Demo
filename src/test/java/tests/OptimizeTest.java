package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OptimizeTest {
private static RequestSpecification requestSpec;
    @BeforeClass
    public void setUrl(){
        //to set Url
        requestSpec=new RequestSpecBuilder().setBaseUri("https://reqres.in/api/").build();
    }

    @Test
    public void testGet()
    {
        int id1= given().spec(requestSpec).when().get("users?page=2").then().extract().path("data.id[0]");
        System.out.println(id1);

   }


}
