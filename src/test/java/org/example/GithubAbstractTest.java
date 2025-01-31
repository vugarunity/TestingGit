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

        // Загружаем переменные окружения из .env
        Dotenv dotenv = Dotenv.load();

        // Получаем значения из .env
        apiKey = dotenv.get("GITHUB_API_KEY");
        baseUrl = dotenv.get("GITHUB_BASE_URL");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

}
