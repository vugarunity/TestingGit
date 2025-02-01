package org.example;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Тестирование проекта GitHub")
@Feature("Тестирование GitHub API для репозиториев")
public class ReadUserProfileTest extends GithubAbstractTest {

    @Test
    @DisplayName("Тест shouldReadRepoSuccessfully - успешное получение всех репозиториев")
    @Description("Данный тест предназначен для получения имеющихся репозиторий")
    @Link("https://api.github.com/user/repos")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение существующих репозиториев")
    @Owner("Ализаде Вугар")
    void shouldReadRepoSuccessfully() {

        List<Object> result = given()
                .header("Authorization", "token " + getApiKey())
                .when()
                .get(getBaseUrl() + "/user/repos")
                .then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getList(".");

//        Assertions.assertEquals(28, result.size());
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty(), "Список репозиториев не должен быть пустым.");
    }

    @Test
    @DisplayName("Тест shouldFailToReadRepoWithInvalidData - неудачное получение всех репозиториев")
    @Description("Тест проверяет обработку ошибки при получении репозиториев с некорректными данными")
    @Link("https://api.github.com/user/repos")
    @Severity(SeverityLevel.NORMAL)
    @Story("Ошибка при получении репозиториев")
    @Owner("Ализаде Вугар")
    void shouldFailToReadRepoWithInvalidData() {
        String errorMessage = given()
                .header("Authorization", "token " + getApiKey())
                .when()
                .get(getBaseUrl() + "/user/repo")
                .then()
                .statusCode(404)
                .extract()
                .path("message");

        assertThat(errorMessage).isEqualTo("Not Found");
    }

}
