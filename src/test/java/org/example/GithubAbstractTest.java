package org.example;

import io.restassured.RestAssured;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;

public class GithubAbstractTest {

    private static String apiKey;
    private static String baseUrl;
    private String id;

    @BeforeAll
    static void initTest() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Загружаем .env, если он есть
        Dotenv dotenv;
        try {
            dotenv = Dotenv.load();
            apiKey = dotenv.get("APIKEY");
            baseUrl = dotenv.get("BASE_URL");
        } catch (Exception e) {
            System.out.println("Файл .env не найден, используем переменные окружения");
            apiKey = System.getenv("APIKEY");
            baseUrl = System.getenv("BASE_URL");
        }
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

}
