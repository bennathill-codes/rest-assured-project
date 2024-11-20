package com.jsonplaceholder.test.endpoints;

import com.jsonplaceholder.test.data.Post;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static com.jsonplaceholder.test.utils.TestHelpers.postBuilder;
import static com.jsonplaceholder.test.utils.TestHelpers.createNewPost;

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
    public void testGetPostComments() {
        Response response = when()
                    .get("/posts/1/comments")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();

        int totalPostComments = response.jsonPath().getList("$").size();
        assertThat(totalPostComments).isEqualTo(5);
    }

    @Test
    public void testCreateNewPost() {
        Post post = postBuilder(1, 1,"new post", "new post body");

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
    public void testPutPost() {
        Post post = postBuilder(1, 1, "put post", "put post body");

        Response response =
                given()
                    .header("Content-Type", "application/json")
                    .body(post)
                .when()
                    .put("posts/100")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        Post putPost = response.getBody().as(Post.class);
        assertThat(putPost.getId()).isEqualTo(100);
        assertThat(putPost.getUserId()).isEqualTo(post.getUserId());
        assertThat(putPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(putPost.getBody()).isEqualTo(post.getBody());
    }

    @Test
    public void testPatchPost() {
        Post post = postBuilder(1,1, "patch post", "patch body");

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

    @Test
    public void testDeletePost() {
        Post newPost = postBuilder(1001, 1001, "to be deleted post", "to be deleted post body");
        createNewPost(newPost);

        Response response =
                given()
                    .header("Content-Type", "application/json")
                .when()
                    .delete("/posts/" + newPost.getId())
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        Post deletePost = response.getBody().as(Post.class);
        assertThat(deletePost.getId()).isNull();
        assertThat(deletePost.getUserId()).isNull();
        assertThat(deletePost.getTitle()).isNull();
        assertThat(deletePost.getBody()).isNull();
    }
}
