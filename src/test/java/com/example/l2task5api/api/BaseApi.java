package com.example.l2task5api.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApi {

    private final RequestSpecification requestSpecification;

    public BaseApi(String baseUri) {
        this.requestSpecification = RestAssured.given().baseUri(baseUri);
    }

    protected Response get(String basePath) {
        return requestSpecification
                .basePath(basePath)
                .when().get()
                .then()
                .extract().response();
    }

    protected Response post(String basePath, String body) {
        return requestSpecification
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when().post(basePath)
                .then()
                .extract().response();
    }
}
