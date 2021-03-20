package com.wzmxx.code.utils;

public class NameUtils {

    public static String getKebabName(String name) {
        String kebabName = "";
        int length = name.length();
        for (int i = 0; i < length; i ++) {
            char character = name.charAt(i);
            // 大写字母
            if (character >= 65 && character <= 90) {
                String lowerCase = String.valueOf(character).toLowerCase();
                if (i == 0) {
                    kebabName += lowerCase;
                } else {
                    char lastCharcter = name.charAt(i - 1);
                    // 上一个字符也为大写，不加 -
                    if (lastCharcter >= 65 && lastCharcter <= 90) {
                        kebabName += lowerCase;
                    } else {
                        kebabName += "-" + lowerCase;
                    }
                }
            } else  {
                kebabName += character;
            }
        }
        return kebabName;
    }

    public static String getUnderScoreName(String name) {
        String underName = "";
        int length = name.length();
        for (int i = 0; i < length; i ++) {
            char character = name.charAt(i);
            // 大写字母
            if (character >= 65 && character <= 90) {
                String lowerCase = String.valueOf(character).toLowerCase();
                if (i == 0) {
                    underName += lowerCase;
                } else {
                    char lastCharcter = name.charAt(i - 1);
                    // 上一个字符也为大写，不加 -
                    if (lastCharcter >= 65 && lastCharcter <= 90) {
                        underName += lowerCase;
                    } else {
                        underName += "_" + lowerCase;
                    }
                }
            } else  {
                underName += character;
            }
        }
        return underName;
    }
}
