
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresApiTests {
    @Test
    void getListOfUsersPerPageTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("per_page", is(6));
    }

    @Test
    void getSingleUserTotalTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void getSingleUserNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/456")
                .then()
                .statusCode(404);
    }

    @Test
    void getListResourceTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void getSingleResourceTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/6")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.name", is("blue turquoise"));

    }

    @Test
    void getSingleResourceNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/456")
                .then()
                .statusCode(404);
    }

    @Test
    void createUserTest() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"Shelby\",\n" +
                        "    \"job\": \"engineer\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("Shelby"));
    }

    @Test
    void updateUserTest() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"Shelby\",\n" +
                        "    \"job\": \"engineer\"\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/4")
                .then()
                .statusCode(200)
                .body("name", is("Shelby"));
    }
}