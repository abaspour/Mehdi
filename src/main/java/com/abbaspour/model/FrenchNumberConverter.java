package com.abbaspour.model;
import java.util.HashMap;

public class FrenchNumberConverter {
    private static final String[] units = {"", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf"};
    private static final String[] tens = {"", "", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante-dix", "quatre-vingt", "quatre-vingt-dix"};

    private static final HashMap<Integer, String> numberMap = new HashMap<Integer, String>();

    static {
        numberMap.put(0, "zéro");
        numberMap.put(100, "cent");
        numberMap.put(1000, "mille");
        numberMap.put(1000000, "million");
        numberMap.put(1000000000, "milliard");
    }

    public static String convertToFrench(int n) {
        if (n < 0) {
            return "moins " + convertToFrench(-n);
        }

        if (n < 20) {
            return units[n];
        }

        if (n < 100) {
            int t = n / 10;
            int u = n % 10;

            if (u == 0) {
                return tens[t];
            } else if (t == 7 || t == 9) {
                return tens[t] + "-" + units[10 + u];
            } else {
                return tens[t] + "-" + units[u];
            }
        }

        for (int number : numberMap.keySet()) {
            if (n < number * 1000) {
                int quotient = n / number;
                int remainder = n % number;

                String word = numberMap.get(number);
                if (number == 100) {
                    if (quotient == 1 && remainder == 0) {
                        return word;
                    } else if (quotient == 1) {
                        return word + " " + convertToFrench(remainder);
                    } else if (remainder == 0) {
                        return convertToFrench(quotient) + " " + word;
                    } else {
                        return convertToFrench(quotient) + " " + word + " " + convertToFrench(remainder);
                    }
                } else {
                    if (quotient == 1 && remainder == 0) {
                        return convertToFrench(number) + " " + word;
                    } else if (quotient == 1) {
                        return convertToFrench(number) + " " + word + " " + convertToFrench(remainder);
                    } else if (remainder == 0) {
                        return convertToFrench(quotient) + " " + word;
                    } else {
                        return convertToFrench(quotient) + " " + word + " " + convertToFrench(remainder);
                    }
                }
            }
        }

        return "";
    }
}
