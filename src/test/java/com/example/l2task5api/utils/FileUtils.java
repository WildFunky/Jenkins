package com.example.l2task5api.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class FileUtils {
    private final static ISettingsFile testData = new JsonSettingsFile("testData.json");

    public static int getIntValueFromTestData(String key) {
        return Integer.parseInt(testData.getValue(key).toString());
    }
}
