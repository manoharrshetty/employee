package com.test;

public class NameUtils {

    public static String cleanName(String name) {
        if (name == null) {
            return null;
        }
        return name.trim().toLowerCase();
    }
}
