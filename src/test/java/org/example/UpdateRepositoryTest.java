package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpdateRepositoryTest extends GithubAbstractTest {

    @Test
    void shouldUpdateRepoSuccessfully() {

        // Тело запроса для создания репозитория
        String requestBody = """
                {
                    "name": "vnbsu",
                    "description": "This is your first updated repository",
                    "private": false
                }
                """;

        // Выполнение POST запроса для создания репозитория
        String responseBody = given()
                .header("Authorization", "token " + getApiKey())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .patch(getBaseUrl() + "/repos/vugarunity/vnbs")
                .then()
                .statusCode(200) // Ожидаем код ответа 201 для успешного создания
                .extract()
                .body().asString();

        // Проверка, что репозиторий был создан, и его имя совпадает с ожидаемым
        Assertions.assertTrue(responseBody.contains("\"name\":\"vnbsu\""), "Репозиторий был обновлен.");
    }

    @Test
    void shouldFailToUpdateRepoWithInvalidData() {

        String requestBody = """
                {
                    "name": "vnbsu",
                    "description": "This is your first updated repository",
                    "private": false
                }
                """;

        // Выполнение POST запроса для создания репозитория
        String errorMessage = given()
                .header("Authorization", "token " + getApiKey())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .patch(getBaseUrl() + "/repos/vugarunity/vnb")
                .then()
                .statusCode(404) // Ожидаем код ответа 201 для успешного создания
                .extract()
                .path("message");

        assertThat(errorMessage).isEqualTo("Not Found");
    }
}
