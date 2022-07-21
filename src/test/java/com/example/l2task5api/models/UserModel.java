package com.example.l2task5api.models;

import lombok.Data;

@Data
public class UserModel {
    private long id;
    private String name;
    private String username;
    private String email;
    private AddressModel address;
    private String phone;
    private String website;
    private CompanyModel company;
}
