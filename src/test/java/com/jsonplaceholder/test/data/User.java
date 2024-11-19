package com.jsonplaceholder.test.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}

