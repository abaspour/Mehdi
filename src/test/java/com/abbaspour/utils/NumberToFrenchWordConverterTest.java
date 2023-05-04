package com.abbaspour.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberToFrenchWordConverterTest {

    @Test
    public void testFrenchRegularWithoutAnd() {
        String expectedValue = "Cent";

        String word = NumberToFrenchWordConverter.convert(100L);
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchRegularWithoutAndAboveHundred() {
        String expectedValue = "Cent soixante mille cent";

        String word = NumberToFrenchWordConverter.convert(160100L);
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchRegular() {
        String expectedValue = "Cent treize";

        String word = NumberToFrenchWordConverter.convert(113L);
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchRegularLessThanTwenty() {
        String word = NumberToFrenchWordConverter.convert(13L);

        String expectedValue = "Treize";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchRegularLessThanHundred() {
        String word = NumberToFrenchWordConverter.convert(85L);

        String expectedValue = "Quatre-vingt-cinq";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchRegularThousands() {
        String word = NumberToFrenchWordConverter.convert(5237L);

        String expectedValue = "Cinquante-deux cent trente-sept";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchRegularNegativeTen() {
        String word = NumberToFrenchWordConverter.convert(-10L);

        String expectedValue = "Moins dix";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchEdgeCase() {
        String word = NumberToFrenchWordConverter.convert(-0L);

        String expectedValue = "Zéro";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchNegativeNumber() {
        String word = NumberToFrenchWordConverter.convert(-85L);

        String expectedValue = "Moins quatre-vingt-cinq";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchEdgeCaseIntLimitMax() {
        String word = NumberToFrenchWordConverter.convert(2147483647L);

        String expectedValue = "Deux milliard cent quarante-sept million quatre cent quatre-vingt-trois" +
                " mille six cent quarante-sept";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testFrenchEdgeCaseIntLimitMin() {
        String word = NumberToFrenchWordConverter.convert(-2147483648L);

        String expectedValue = "Moins deux milliard cent quarante-sept million quatre cent " +
                "quatre-vingt-trois mille six cent quarante-huit";
        assertEquals(expectedValue, word);
    }
}
