package com.jsonplaceholder.test.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentsTest {
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetAllComments() {
        Response response =
                when()
                    .get("/comments")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();

        int totalComments = response.jsonPath().getList("$").size();
        assertThat(totalComments).isEqualTo(500);
    }

    @Test
    public void testGetAllCommentsOnPost() {
        Response response =
                when()
                    .get("/comments?postId=1")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();

        int totalPostComments = response.jsonPath().getList("$").size();
        assertThat(totalPostComments).isEqualTo(5);
    }
}
