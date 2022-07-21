package com.example.l2task5api.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {

    public static String getRandomText(int numOfWords) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < numOfWords; i++) {
            String  word = RandomStringUtils.randomAlphabetic(1, 10);
            text.append(word).append(" ");
        }
        return text.toString().trim();
    }
}
