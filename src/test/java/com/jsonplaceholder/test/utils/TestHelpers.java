package com.jsonplaceholder.test.utils;

import com.jsonplaceholder.test.data.Post;

import static io.restassured.RestAssured.given;

public class TestHelpers {

    public static Post postBuilder(Integer id, Integer userId, String title, String body) {
        return Post.builder()
                .id(id != null ? id : 0)// default id if null
                .userId(userId != null ? userId : 0) // default userId if null
                .title(title != null ? title : "Post Title") // default title if null
                .body(body != null ? body : "Post Body") // default body if null
                .build();
    }

    public static void createNewPost(Post newPost) {
        given()
            .header("Content-Type", "application/json")
            .body(newPost)
        .when()
            .post("/posts")
        .then()
            .statusCode(201);
    }
}
