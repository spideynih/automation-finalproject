package utils;

import java.util.Random;

public class TestDataUtils {

    public static String generateSpideyEmail() {
        int random = new Random().nextInt(100000);
        return "spidey" + random + "@mail.id";
    }

    public static String generateEmployeeId() {
        int random = new Random().nextInt(100000);
        return "EMP-" + random;
    }

    public static String generatePhone() {
        int random = new Random().nextInt(100000000);
        return "812" + random;
    }
}