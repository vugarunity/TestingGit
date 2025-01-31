package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateRepositoryTest extends GithubAbstractTest{

    @Test
    void shouldCreateRepoSuccessfully () {

        // Тело запроса для создания репозитория
        String requestBody = """
                {
                    "name": "vnbs",
                    "description": "This is your first repository",
                    "private": false
                }
                """;

        // Выполнение POST запроса для создания репозитория
        String responseBody = given()
                .header("Authorization", "token " + getApiKey())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(getBaseUrl() + "/user/repos")
                .then()
                .statusCode(201) // Ожидаем код ответа 201 для успешного создания
                .extract()
                .body().asString();

        // Проверка, что репозиторий был создан, и его имя совпадает с ожидаемым
        Assertions.assertTrue(responseBody.contains("\"name\":\"vnbs\""), "Репозиторий был создан.");
    }

    @Test
    void shouldReturn422WhenCreatingRepoWithExistingName() {
        String requestBody = """
                {
                    "name": "vnbs",
                    "description": "This is your first repository",
                    "private": false
                }
                """;

        // Выполнение POST запроса с неверным путем
        String errorMessage = given()
                .header("Authorization", "token " + getApiKey())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(getBaseUrl() + "/user/repos") // исправлен путь
                .then()
                .statusCode(422) // GitHub вернет 422 Unprocessable Entity, если название уже существует
                .extract()
                .path("message");

        // Логирование и проверка ошибки
//        System.out.println("Полученное сообщение об ошибке: " + errorMessage);
        assertThat(errorMessage).contains("Repository creation failed");
    }

    @Test
    void shouldFailToCreateRepoWithInvalidData() {
        String requestBody = """
                {
                    "name": "vnbs",
                    "description": "This is your first repository",
                    "private": false
                }
                """;

        // Выполнение POST запроса для создания репозитория
        String errorMessage = given()
                .header("Authorization", "token " + getApiKey())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(getBaseUrl() + "/user/repo")
                .then()
                .statusCode(404) // Ожидаем код ответа 201 для успешного создания
                .extract()
                .path("message");

        assertThat(errorMessage).isEqualTo("Not Found");
    }
}
