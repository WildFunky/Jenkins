package com.example.l2task5api.models;

import lombok.Data;

@Data
public class AddressModel {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoModel geo;
}
