package net.adamcowell14.graytan.util;

import java.util.Random;

public class Utils {
    public static String getSaltString() {
        String SALTCHARS = "qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        return salt.toString();
    }

    public static Boolean regexString(String input) {
        return !input.matches("^[a-zA-Z0-9_-]*$");
    }
}
