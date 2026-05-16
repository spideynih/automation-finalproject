package utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestDataLoader {

    public static String load(String path) {

        InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path);

        if (is == null) {
            throw new RuntimeException("File NOT FOUND in resources: " + path);
        }

        try {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed reading file: " + path, e);
        }
    }
}