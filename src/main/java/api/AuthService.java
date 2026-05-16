package api;

import io.restassured.response.Response;
import models.ApiResponse;
import models.LoginResponse;
import models.LoginVariables;
import utils.TestDataLoader;

public class AuthService {

    public static ApiResponse<LoginResponse> postLogin(
            String email, String password, String companyId) {

        String query = TestDataLoader.load("graphql/Login.graphql");
        LoginVariables variables = new LoginVariables(email, password, companyId);

        Response response = GraphQLClient.execute(query, variables);

        System.out.println("LOGIN RESPONSE: " + response.getBody().asString());
        System.out.println("ALL COOKIES: " + response.getCookies());
        System.out.println("SET-COOKIE HEADER: " + response.getHeader("Set-Cookie"));

        String sid = response.getCookie("sid_b2b");
        System.out.println("SID COOKIE: " + sid);

        if (sid != null) {
            AuthSession.setSessionCookie(sid);
            System.out.println("COOKIE SAVED: " + sid);
        }

        return new ApiResponse<>(
                response.getStatusCode(),
                response.getHeaders(),
                response.as(LoginResponse.class)
        );
    }
}