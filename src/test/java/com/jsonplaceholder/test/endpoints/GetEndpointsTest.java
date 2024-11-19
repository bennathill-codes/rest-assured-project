package com.jsonplaceholder.test.endpoints;

import com.jsonplaceholder.test.data.Post;
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
    public void testGetAllPosts() {
        Response response =
                when().get("/posts")
                        .then().assertThat().statusCode(200)
                        .extract().response();

        int totalPosts = response.jsonPath().getList("$").size();
        assertThat(totalPosts).isEqualTo(100);
    }

    @Test
    public void testGetPost() {
        Response response =
                when().get("/posts/1")
                        .then().assertThat().statusCode(200)
                        .extract().response();

        Post post = response.getBody().as(Post.class);
        assertThat(post.getUserId()).isEqualTo(1);
        assertThat(post.getTitle()).isNotNull();
        assertThat(post.getBody()).isNotNull();
        assertThat(post.getId()).isNotZero();
    }

    @Test
    public void testGetAllComments() {
        Response response =
                when().get("/comments")
                        .then().assertThat().statusCode(200)
                        .extract().response();


    }
}
