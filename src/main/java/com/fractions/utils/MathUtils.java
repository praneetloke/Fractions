package com.fractions.utils;

public class MathUtils {
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return MathUtils.gcd(b, a % b);
    }
}
