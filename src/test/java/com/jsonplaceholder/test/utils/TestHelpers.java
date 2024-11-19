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
}
