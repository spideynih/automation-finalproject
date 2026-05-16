package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String get(String key) {
        String value = System.getenv(key);
        return value != null ? value : dotenv.get(key);
    }

    public static final String BASE_URL = get("BASE_URL");
    public static final String API_BASE_URL = get("API_BASE_URL");
}