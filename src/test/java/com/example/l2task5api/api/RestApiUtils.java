package com.example.l2task5api.api;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.response.Response;

public class RestApiUtils extends BaseApi {
    private final static ISettingsFile apiSettings = new JsonSettingsFile("apiSettings.json");
    private final static String baseUri = apiSettings.getValue("/baseUri").toString();
    private final String posts = "/posts";
    private final String users = "/users";

    public RestApiUtils() {
        super(baseUri);
    }

    public Response getAllPosts() {
        return get(posts);
    }

    public Response getPostById(int id) {
        return get(posts + "/" + id );
    }

    public Response postNewPost(String body) {
        return post(posts, body);
    }

    public Response getAllUsers() {
        return get(users);
    }

    public Response getUserById(int id) {
        return get(users + "/" + id);
    }
}
