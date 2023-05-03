package com.abbaspour.utils;
import java.util.HashMap;
/**
 * @author Mehdi Abbaspour
 * @version 1.2
 * created: May 1, 2023
 * updated: May 3, 2023
 */
public class NumberToFrenchWordConverter  {
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

    /**
     * Transform any 32-bit integer number into French word equivalents
     * @param n number
     * @return String
     */
    public static String convert(Long n) {
        if (n == 0)
            return "Zéro";

        String word=convert2fr(n);
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    public static String convert2fr(long n) {

        if (n < 0) {
            return "moins " + convertToFrench(-n);
        }
        return convertToFrench(n);
    }

    public static String convertToFrench(long n) {
        if (n < 0) {
            return "moins " + convertToFrench(-n);
        }

        if (n < 20) {
            return units[(int) n];
        }

        if (n < 100) {
            int t = (int) n / 10;
            int u = (int) n % 10;

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
                int quotient = (int)(n / number);
                int remainder =(int)(n % number);

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
