package com.jsonplaceholder.test.endpoints;

import com.jsonplaceholder.test.data.Post;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class PostsTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//        RestAssured.defaultParser = io.restassured.parsing.Parser.JSON;
    }

    @Test
    public void testGetAllPosts() {
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

    @Test
    public void testGetPost() {
        Response response =
                when()
                    .get("/posts/1")
                .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();

        Post post = response.getBody().as(Post.class);
        assertThat(post.getId()).isNotZero();
        assertThat(post.getUserId()).isEqualTo(1);
        assertThat(post.getTitle()).isNotNull();
        assertThat(post.getBody()).isNotNull();
    }

    @Test
    public void testPostNewPost() {
        Post post = Post.builder()
                .userId(1)
                .title("new post")
                .body("new post body")
                .build();

        Response response =
                given()
                    .header("Content-Type", "application/json")
                    .body(post)
                .when()
                    .post("/posts")
                .then()
                    .statusCode(201)
                    .extract()
                    .response();

        Post newPost = response.getBody().as(Post.class);
        assertThat(newPost.getId()).isNotZero();
        assertThat(newPost.getUserId()).isEqualTo(post.getUserId());
        assertThat(newPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(newPost.getBody()).isEqualTo(post.getBody());
    }
}
