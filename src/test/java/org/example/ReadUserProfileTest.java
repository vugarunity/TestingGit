package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReadUserProfileTest extends GithubAbstractTest {

    @Test
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
