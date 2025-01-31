package org.example;
import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String API_KEY = dotenv.get("GITHUB_API_KEY");
    public static final String BASE_URL = dotenv.get("GITHUB_BASE_URL");

    public static void main(String[] args) {
        System.out.println("API Key: " + API_KEY);
        System.out.println("Base URL: " + BASE_URL);
    }
}

