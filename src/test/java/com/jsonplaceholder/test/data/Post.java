package com.jsonplaceholder.test.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private int id;
    private String title;
    private String body;
    private int userId;
}
