package com.jsonplaceholder.test.endpoints;

import com.jsonplaceholder.test.data.Post;
import com.jsonplaceholder.test.utils.TestHelpers;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class PostsTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
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
    public void testCreateNewPost() {
        Post post = TestHelpers.postBuilder(1, "new post", "new post body");

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

    @Test
    public void testPatchPost() {
        Post post = TestHelpers.postBuilder(1,1, "patch post", "patch post body");

        Response response =
                given()
                    .header("Content-Type", "application/json")
                    .body(post)
                .when()
                    .patch("/posts/100")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        Post patchPost = response.getBody().as(Post.class);
        assertThat(patchPost.getId()).isNotZero();
        assertThat(patchPost.getUserId()).isEqualTo(post.getUserId());
        assertThat(patchPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(patchPost.getBody()).isEqualTo(post.getBody());
    }
}
