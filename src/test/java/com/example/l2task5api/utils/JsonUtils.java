package com.example.l2task5api.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    private static final String resourcesPath = "src/resources";

    public static String getJsonStringFromFile(String fileName) {
        String filePath = resourcesPath + File.separator + fileName;
        String jsonString = "";
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
