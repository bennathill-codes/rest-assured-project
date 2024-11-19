package com.jsonplaceholder.test.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private int id;
    private int postId;
    private String name;
    private String email;
    private String body;
}
