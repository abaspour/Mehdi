package com.abbaspour.model;

public class NumberToEngWordConverter {
    private static final String[] ONES = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static final String[] THOUSANDS = {
            "", "thousand", "million", "billion"
    };

    public static String convert(int number) {
        if (number == 0) {
            return "zero";
        }
        if (number < 0) {
            return "negative " + convert(Math.abs(number));
        }

        String word = "";

        for (int i = 0; number > 0; i++) {
            if (number % 1000 != 0) {
                word = convertLessThan1000(number % 1000) + THOUSANDS[i] + " " + word;
            }
            number /= 1000;
        }

        return word.trim();
    }

    private static String convertLessThan1000(int number) {
        if (number == 0) {
            return "";
        }
        if (number < 20) {
            return ONES[number];
        }
        if (number < 100) {
            return TENS[number / 10] + " " + ONES[number % 10];
        }
        return ONES[number / 100] + " hundred " + convertLessThan1000(number % 100);
    }
}
