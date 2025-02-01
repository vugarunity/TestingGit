package org.example;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Тестирование проекта GitHub")
@Feature("Тестирование GitHub API для репозиториев")
public class UpdateRepositoryTest extends GithubAbstractTest {

    @Test
    @DisplayName("Тест shouldUpdateRepoSuccessfully - успешное обновление репозитория")
    @Description("Данный тест предназначен для обновления репозитория")
    @Link("https://api.github.com/repos/vugarunity/vnbs")
    @Severity(SeverityLevel.NORMAL)
    @Story("Успешное обновление репозитория")
    @Owner("Ализаде Вугар")
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
    @DisplayName("Тест shouldFailToUpdateRepoWithInvalidData - неудачное обновление репозитория")
    @Description("Тест проверяет обработку ошибки при обновлении репозитория с некорректными данными")
    @Link("https://api.github.com/repos/vugarunity/vnbs")
    @Severity(SeverityLevel.NORMAL)
    @Story("Ошибка при обновлении репозитория")
    @Owner("Ализаде Вугар")
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
