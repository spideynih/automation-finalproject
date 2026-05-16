package api;

public class AuthSession {

    private static String sessionCookie;

    public static void setSessionCookie(String cookie) {
        sessionCookie = cookie;
    }

    public static String getSessionCookie() {
        return sessionCookie;
    }
}