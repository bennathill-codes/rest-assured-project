package com.jsonplaceholder.test.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private Integer id;
    private String title;
    private String body;
    private Integer userId;
}
