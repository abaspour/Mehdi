package com.abbaspour.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberToEnglishWordConverterTest {

    @Test
    public void testNumberToWordEngRegularWithoutAnd() {
        String expectedValue = "One hundred";

        String word = NumberToEnglishWordConverter.convert(100L);
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngRegularWithoutAndAboveHundred() {
        String expectedValue = "One hundred sixty thousand one hundred";

        String word = NumberToEnglishWordConverter.convert(160100L);
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngRegular() {
        String expectedValue = "One hundred and thirteen";

        String word = NumberToEnglishWordConverter.convert(113L);
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngRegularLessThanTwenty() {
        String word = NumberToEnglishWordConverter.convert(13L);

        String expectedValue = "Thirteen";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngRegularLessThanHundred() {
        String word = NumberToEnglishWordConverter.convert(85L);

        String expectedValue = "Eighty five";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngRegularThousands() {
        String word = NumberToEnglishWordConverter.convert(5237L);

        String expectedValue = "Five thousand two hundred and thirty seven";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngRegularNegativeTen() {
        String word = NumberToEnglishWordConverter.convert(-10L);

        String expectedValue = "Negative ten";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngEdgeCase() {
        String word = NumberToEnglishWordConverter.convert(-0L);

        String expectedValue = "Zero";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngNegativeNumber() {
        String word = NumberToEnglishWordConverter.convert(-85L);

        String expectedValue = "Negative eighty five";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngEdgeCaseIntLimitMax() {
        String word = NumberToEnglishWordConverter.convert(2147483647L);

        String expectedValue = "Two billion one hundred forty seven million four hundred eighty" +
                " three thousand six hundred and forty seven";
        assertEquals(expectedValue, word);
    }

    @Test
    public void testNumberToWordEngEdgeCaseIntLimitMin() {
        String word = NumberToEnglishWordConverter.convert(-2147483648L);

        String expectedValue = "Negative two billion one hundred forty seven million four hundred eighty " +
                "three thousand six hundred and forty eight";
        assertEquals(expectedValue, word);
    }
}