package com.jsonplaceholder.test.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
