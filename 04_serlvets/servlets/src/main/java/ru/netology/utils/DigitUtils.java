package ru.netology.utils;

public class DigitUtils {

    private DigitUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int getDigit(String pathWithDigit) {
        return Integer.parseInt(pathWithDigit.substring(pathWithDigit.lastIndexOf("/") + 1));
    }
}
