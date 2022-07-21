package com.example.l2task5api.tests;

import com.example.l2task5api.api.RestApiUtils;
import com.example.l2task5api.models.PostModel;
import com.example.l2task5api.models.UserModel;
import com.example.l2task5api.utils.FileUtils;
import com.example.l2task5api.utils.JsonUtils;
import com.example.l2task5api.utils.RandomUtil;
import com.google.common.collect.Ordering;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.example.l2task5api.enums.ContentTypes.*;
import static com.example.l2task5api.enums.StatusCodes.*;

public class RestApiTest {

    private final RestApiUtils restApiUtils = new RestApiUtils();

    @Test
    public void restApiTest() {

        Response allPostsResponse = restApiUtils.getAllPosts();
        Assert.assertEquals(allPostsResponse.getStatusCode(), OK.getValue(),
                "Get all posts response status code is not OK");
        Assert.assertEquals(allPostsResponse.getContentType(), JSON.getValue(),
                "Get all posts response content type is not JSON");

        List<String> id = allPostsResponse
                .jsonPath()
                .getList("id");
        Assert.assertTrue(Ordering.natural().isOrdered(id),
                "Posts are not ordered!");

        int postId = FileUtils.getIntValueFromTestData("/getPostById");
        int postOwnerId = FileUtils.getIntValueFromTestData("/getPostByIdOwnerId");
        Response getPostByIdResponse = restApiUtils.getPostById(postId);
        Assert.assertEquals(getPostByIdResponse.getStatusCode(), OK.getValue(),
                "Get post by ID response status code is not OK");

        PostModel postById = getPostByIdResponse.getBody().as(PostModel.class);
        Assert.assertEquals(postById.getUserId(), postOwnerId, "User ID is not match!");
        Assert.assertEquals(postById.getId(), postId, "Post ID is not match");
        Assert.assertFalse(postById.getTitle().isEmpty());
        Assert.assertFalse(postById.getBody().isEmpty());

        int noPostId = FileUtils.getIntValueFromTestData("/getNoPostById");
        Response getNoPostResponse = restApiUtils.getPostById(noPostId);
        Map<Object, Object> noPostBody = getNoPostResponse.getBody().jsonPath().getMap("");
        Assert.assertEquals(getNoPostResponse.getStatusCode(), NOT_FOUND.getValue(),
                "Get no post response status code is not NOT FOUND");
        Assert.assertTrue(noPostBody.isEmpty(),
                "Get no post response is not empty");

        PostModel testPost = new PostModel();
        testPost.setUserId(1);
        testPost.setTitle(RandomUtil.getRandomText(2));
        testPost.setBody(RandomUtil.getRandomText(10));

        Gson gson = new Gson();
        String testPostBody = gson.toJson(testPost);

        Response postPostResponse = restApiUtils.postNewPost(testPostBody);
        Assert.assertEquals(postPostResponse.getStatusCode(), CREATED.getValue(),
                "Post post response status code is not CREATED");

        int expectedPostId = FileUtils.getIntValueFromTestData("/expectedPostId");
        PostModel actualPost = postPostResponse.body().as(PostModel.class);
        Assert.assertEquals(actualPost.getId(), expectedPostId, "Posts id's do not match!");
        Assert.assertEquals(actualPost.getUserId(), testPost.getUserId(), "Posts userId's do not match!");
        Assert.assertEquals(actualPost.getTitle(), testPost.getTitle(), "Posts titles do not match!");
        Assert.assertEquals(actualPost.getBody(), testPost.getBody(), "Posts bodies do not match!");

        String userJson = JsonUtils.getJsonStringFromFile("testUser.json");
        UserModel testUser = gson.fromJson(userJson, UserModel.class);

        Response getAllUsersResponse = restApiUtils.getAllUsers();
        Assert.assertEquals(getAllUsersResponse.getStatusCode(), OK.getValue(),
                "Get all users response status code is not OK");
        Assert.assertEquals(getAllUsersResponse.contentType(), JSON.getValue(),
                "Get all users response is not JSON");

        List<UserModel> users = getAllUsersResponse.jsonPath().getList("", UserModel.class);
        UserModel actualUser = users.stream()
                .filter(user -> user.getId() == testUser.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User not found!"));
        Assert.assertEquals(actualUser, testUser, "Users not equals!");

        int userId = FileUtils.getIntValueFromTestData("/getUserBy");
        Response getUserByIdResponse = restApiUtils.getUserById(userId);
        Assert.assertEquals(getAllUsersResponse.getStatusCode(), OK.getValue(),
                "Get user by ID response status code is not OK");
        UserModel userById = getUserByIdResponse.body().as(UserModel.class);
        Assert.assertEquals(userById, testUser, "Users not equals!");
    }
}
