package org.example;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Тестирование проекта GitHub")
@Feature("Тестирование GitHub API для репозиториев")
public class DeleteRepositoryTest extends GithubAbstractTest {

    // Сделать переменную полем класса, чтобы она была доступна в разных методах
    String repositoryName = "vnbsu"; // Имя репозитория

    @Test
    @DisplayName("Тест shouldDeleteRepositorySuccessfully - успешное удаления репозитория")
    @Description("Данный тест преднозначен для удаления репозитория")
    @Link("https://api.github.com/repos/vugarunity/vnbsu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Удаление существующего репозитория")
    @Owner("Ализаде Вугар")
    void shouldDeleteRepositorySuccessfully() {

        // Удалить созданный репозиторий
        given()
                .header("Authorization", "token " + getApiKey())
                .when()
                .delete(getBaseUrl() + "/repos/{owner}/{repo}", "vugarunity", repositoryName)
                .then()
                .statusCode(204); // Код 204 — успешное удаление без содержимого

        // Проверить, что репозиторий действительно удален
        int statusCode = given()
                .header("Authorization", "token " + getApiKey())
                .when()
                .get(getBaseUrl() + "/repos/{owner}/{repo}", "vugarunity", repositoryName)
                .getStatusCode();

        assertThat(statusCode).isEqualTo(404); // Код 404 — репозиторий не найден
    }
}
