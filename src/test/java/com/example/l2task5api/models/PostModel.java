package com.example.l2task5api.models;

import lombok.Data;

@Data
public class PostModel {
    private int userId;
    private int id;
    private String title;
    private String body;
    private int postId;
}
