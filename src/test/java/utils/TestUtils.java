package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class TestUtils {

    private static final String BASE_URL = "https://openweathermap.org/";

    public static String getBaseUrl() {

        return BASE_URL;
    }

    public static String getRandomName(int length) {

        return RandomStringUtils
                .random(length,
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }

    public static String getRandomName() {

        return getRandomName(7);
    }
}
