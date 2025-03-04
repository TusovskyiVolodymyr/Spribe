package org.spribe.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.List;

public class RandomUtils {
    private static final Random RANDOM = new Random();
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static int randomInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static <T> T randomElement(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    public static <T> T randomElement(T[] array) {
        return Arrays.asList(array).get(RANDOM.nextInt(array.length));
    }

    public static <T> void shuffleList(List<T> list) {
        java.util.Collections.shuffle(list, RANDOM);
    }
}
