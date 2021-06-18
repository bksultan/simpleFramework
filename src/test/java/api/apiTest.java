package api;

import api.models.Entries;
import api.models.Envelope;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class apiTest {
    static String baseURI = "https://api.publicapis.org/";

    public static Response baseCall(String path) {
        return given()
                .accept(ContentType.JSON)
                .when()
                .get(baseURI + path).thenReturn();
    }

    @Test
    public void artistTest() {
        Response response = baseCall("entries");
        response.prettyPrint();

        ArrayList<Entries> entries = response.as(EntriesEnvelope.class).getEntries();

        System.out.println("test " + entries.get(0).getAPI());
    }

    private static class EntriesEnvelope extends Envelope<Entries> {

    }
}


