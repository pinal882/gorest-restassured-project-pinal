package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //RestAssured.port= 3030;
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=20")
                .then().statusCode(200);
    }
    //1. Extract the title
    @Test
    public void test001(){
        List<String >title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the total number of record
    @Test
    public void test002(){
        int record = response.extract().path("size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total  no of record is  : " + record);
        System.out.println("------------------End of Test---------------------------");

    }
    //3. Extract the body of 15th record
    @Test
    public void test003(){
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the user_id of all the records
    @Test
    public void test004(){
        List<Integer> userid = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The userId of all the records are : "+userid );
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the title of all the records
    @Test
    public void test005(){
        List<String > title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records are : "+ title );
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Extract the title of all records whose user_id = 5914200
    @Test
    public void test006(){
        System.out.println("The Response Body Of Record Having ID As 5914254 : " + response.extract().path("findAll{it.user_id == 5914254}.title"));
      // List <String> title = response.extract().path("findAll{it.user_id == 5914253}.title");
       // System.out.println("------------------StartingTest---------------------------");
        //System.out.println("The title of all records for user id 5914253: " + title);
       // System.out.println("------------------End of Test---------------------------");

    }
    //7. Extract the body of all records whose id = 93957
    @Test
    public void test007(){
        System.out.println("The Response Body Of Record Having ID As 93999 : " + response.extract().path("findAll{it.id == 93999}.body"));
        //List<String> body = response.extract().path("findAll{it.id == '93997'}.body");
       // System.out.println("------------------StartingTest---------------------------");
       // System.out.println("The value of limit is : " + body);
       // System.out.println("------------------End of Test---------------------------");

    }
}
