package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class GetRequest {
    @Test(enabled = false)
    public void testGet()
    {
        given().when().contentType(ContentType.JSON)
                .get("http://3.13.86.142:3000/contacts")
                        .then().assertThat()
                .statusCode(200);

    }
    @Test(enabled = false)
    public void testGet2_RestoreResponse()
    {
        //To store Response
        Response response= RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.getTime());
        System.out.println(response.getStatusCode());
        System.out.println(response.contentType());
        System.out.println(response.prettyPrint());
    }


    @Test(enabled = false)
    public void ExtractResponseWithoutStoreInResponseClass()
    {
        int totalPages=given().when().get("https://reqres.in/api/users?page=2").then().extract().path("total");
        System.out.println(totalPages);
        int id1=given().when().get("https://reqres.in/api/users?page=2").then().extract().path("data.id[0]");
        System.out.println(id1);
        String avatar=given().when().get("https://reqres.in/api/users?page=2").then().extract().path("data.avatar[2]");
        System.out.println(avatar);
    }


     @Test
    public void  testByBaseUrlWithTestNgAssert()
    {
      given().baseUri("https://reqres.in/api")
            .when().get("/users?page=2")
            .then().log().all().assertThat().body("data.first_name[0]",is(equalTo("Michael")));

      int id_0= given().baseUri("https://reqres.in/api")
              .when().get("/users?page=2")
              .then().extract().path("data.id[0]");
        Assert.assertEquals(id_0, 7);

    }
      @Test
       public void testBaseUrlWithNormalAssert()
     {
    baseURI="https://reqres.in/api";

    given().when().get("/users?page=2").then().assertThat().statusCode(200);
     }
     @Test
    public void extractResponse()
     {
         baseURI="https://reqres.in/api";

       Response response=  given().when().get("/users?page=2").then().extract().response();
         System.out.println(response.asString());

         int id_0Response=response.path("data.id[2]");
         System.out.println(id_0Response);

         String email1= JsonPath.from(response.asString()).getString("data.email[0]");

         System.out.println(email1);

     }
     }
