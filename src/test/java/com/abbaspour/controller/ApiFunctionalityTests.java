package com.abbaspour.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiFunctionalityTests {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testHexadecimalStringRegularOne() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/decode/0xBBF1", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":191,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringRegularTwo() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/decode/0x33A3", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":true,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":58,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringRegularThree() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/decode/0x99C1", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":156,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringRegularFour() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/decode/0x0000", String.class);

        String expectedValue = "{\"machine_on\":false,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":0,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringRegularFive() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/decode/0x0101 ", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":16,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }
    @Test
    public void testHexadecimalStringRegularSix() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/decode/0xF00f ", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":true,\"empty_grounds_fault\":true," +
                "\"water_empty_fault\":true,\"number_of_cups_today\":0,\"descale_required\":true," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }
    @Test
    public void testHexadecimalStringRegularSeven() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/decode/0x0ff0", String.class);

        String expectedValue = "{\"machine_on\":false,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":255,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringErrorHandling() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/decode/0x99Z1", String.class);

        String expectedValue = "Not an acceptable hexadecimal. try like 0x123f";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngRegularWithoutAnd() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/100", String.class);

        String expectedValue = "One hundred";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngRegularWithoutAndAboveHundred() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/160100", String.class);

        String expectedValue = "One hundred sixty thousand one hundred";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngRegular() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/113", String.class);

        String expectedValue = "One hundred and thirteen";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngRegularLessThanTwenty() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/13", String.class);

        String expectedValue = "Thirteen";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngRegularLessThanHundred() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/85", String.class);

        String expectedValue = "Eighty five";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngRegularThousands() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/5 237", String.class);

        String expectedValue = "Five thousand two hundred and thirty seven";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngRegularNegativeTen() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/-10", String.class);

        String expectedValue = "Negative ten";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngEdgeCase() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/-0", String.class);

        String expectedValue = "Zero";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngNegativeNumber() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/-85", String.class);

        String expectedValue = "Negative eighty five";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngEdgeCaseIntLimitMax() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/2147483647", String.class);

        String expectedValue = "Two billion one hundred forty seven million four hundred eighty" +
                " three thousand six hundred and forty seven";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngEdgeCaseIntLimitMin() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/-2147483648", String.class);

        String expectedValue = "Negative two billion one hundred forty seven million four hundred eighty " +
                "three thousand six hundred and forty eight";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }
    @Test
    public void testNumberToWordEngEdgeCaseIntLimitMinSpace() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/-2 147 483 648", String.class);

        String expectedValue = "Negative two billion one hundred forty seven million four hundred eighty " +
                "three thousand six hundred and forty eight";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngErrorEdgeCaseIntLimitMax() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/2147483648", String.class);

        String expectedValue = "over MAX_VALUE.";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngErrorEdgeCaseIntLimitMin() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/-2147483649", String.class);

        String expectedValue = "less than MIN_VALUE.";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngErrorHandlingInvalidNumber() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/-85a", String.class);

        String expectedValue = "not an integer number or length more than 10 digits";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngErrorHandlingTooSmallNumber() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/-9999999999", String.class);

        String expectedValue = "less than MIN_VALUE.";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngErrorHandlingTooLargeNumber() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-word/English/9999999999", String.class);

        String expectedValue = "over MAX_VALUE.";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }
}