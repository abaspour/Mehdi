package com.abbaspour.utils;
/**
 * @author Mehdi Abbaspour
 * @version 1.2
 * created: May 1, 2023
 * updated: May 3, 2023
 */
public class NumberToEnglishWordConverter {
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

    /**
     * Transform any 32-bit integer number into English word equivalents
     * @param number
     * @return String
     */
    public static String convert(Long number){
        if (number == 0) {
            return "Zero";
        }
        String word=convert2eng(number);
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    private static String convert2eng(Long number) {
         if (number < 0) {
            return "negative " + convert2eng(Math.abs(number));
        }

        String word = "";

        boolean shouldAddAndString = number > 100 && number % 100 > 0;

        for (int i = 0; number > 0; i++) {
            if (number % 1000 != 0) {
                word = convertLessThan1000((int)(number % 1000), shouldAddAndString) +" "+ THOUSANDS[i] + " " + word;
                shouldAddAndString = false;
            }
            number /= 1000;
        }

        return word.trim().replace("  "," ");
    }

    private static String convertLessThan1000(int number, boolean shouldAddAndString) {
        String andString = "";
        if (shouldAddAndString) {
            andString = "and ";
        }

        if (number == 0) {
            return "";
        }
        if (number < 20) {
            return andString + ONES[number];
        }
        if (number < 100) {
            return andString + TENS[number / 10] + " " + ONES[number % 10];
        }

        return ONES[number / 100] + " hundred " + andString + convertLessThan1000(number % 100, false);
    }
}
