package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Entry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testDeleteEndpoint() {
        var entry = new Entry();
        entry.setCheckIn(LocalDateTime.now());
        entry.setCheckOut(LocalDateTime.of(2023, 1, 1, 1, 1,1));

        var createResponse = given()
                .contentType("application/json")
                .body(entry)
                .when().post("/entries");

        given()
            .when().delete("/entries/" + createResponse.jsonPath().get("id"))
            .then()
                .statusCode(204);
    }
    @Test
    public void testUpdateEndpoint() {
        var entry = new Entry();
        entry.setCheckIn(LocalDateTime.now());
        entry.setCheckOut(LocalDateTime.of(2020, 1, 1, 1, 1,1));

        

        var updateEntry = new Entry();
        updateEntry.setCheckIn(LocalDateTime.of(2022, 1, 1, 1, 1,1));
        updateEntry.setCheckOut(LocalDateTime.of(2023, 1, 1, 1, 1,1));

        var createResponse = given()
                .contentType(ContentType.JSON)
                .body(entry)
                .when().post("/entries");

        given()
            .contentType(ContentType.JSON)
            .body(updateEntry)
            .when().put("/entries/" + createResponse.jsonPath().get("id"))
            .then()
                .statusCode(200);
    }

}