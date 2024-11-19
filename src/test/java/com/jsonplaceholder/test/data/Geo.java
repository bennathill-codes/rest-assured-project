package com.jsonplaceholder.test.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Geo {
    private String lat;
    private String lng;
}
