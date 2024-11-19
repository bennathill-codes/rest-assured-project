package com.jsonplaceholder.test.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class GetEndpointsTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.defaultParser = io.restassured.parsing.Parser.JSON;
    }

    @Test
    public void testGetPosts() {
        Response response =
            when()
                    .get("/posts")
            .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();

        int totalPosts = response.jsonPath().getList("$").size();
        assertThat(totalPosts).isEqualTo(100);
    }
}
