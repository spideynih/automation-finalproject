package api;

import config.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class GraphQLClient {

    public static Response execute(String query, Object vars) {
        boolean isDebug = "true".equals(EnvConfig.get("DEBUG"));

        RequestSpecification spec = RestAssured.given()
                .baseUri(EnvConfig.get("API_BASE_URL"))
                .contentType(ContentType.JSON);

        if (isDebug) {
            spec.log().all();
        }

        String sessionCookie = AuthSession.getSessionCookie();
        if (sessionCookie != null) {
            spec.cookie("sid_b2b", sessionCookie);
            System.out.println("SENDING COOKIE: " + sessionCookie);
        }

        return spec
                .body(Map.of(
                        "query", query,
                        "variables", vars
                ))
                .when()
                .post("/graphql")
                .then()
                .extract()
                .response();
    }
}