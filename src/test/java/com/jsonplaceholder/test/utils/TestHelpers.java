package com.jsonplaceholder.test.utils;

import com.jsonplaceholder.test.data.Post;

public class TestHelpers {

    public static Post postBuilder(int userId, String title, String body) {
        return Post.builder()
                .userId(userId)
                .title(title)
                .body(body)
                .build();
    }

    public static Post postBuilder(int id, int userId, String title, String body) {
        return Post.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .body(body)
                .build();
    }
}
