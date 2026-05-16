package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    public Data data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        public Login login;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Login {
        public User user;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        public String id;
        public String username;
        public String role;
    }
}