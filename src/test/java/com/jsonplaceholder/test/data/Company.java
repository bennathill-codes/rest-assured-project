package com.jsonplaceholder.test.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
