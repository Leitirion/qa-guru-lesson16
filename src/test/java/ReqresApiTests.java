
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresApiTests {
    @Test
    void getListOfUsersTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void getSingleUserTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2));
    }

    @Test
    void getSingleUserNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void getListResourseTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void getSingleResourseTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", is(2));

    }

    @Test
    void getSingleResourceNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }

    @Test
    void createUserTest() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheuss\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheuss"));
    }

    @Test
    void updateUserTest() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheuss\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", is("morpheuss"));
    }
}