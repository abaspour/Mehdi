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
    public void testHexadecimalStringOne() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0xBBF1", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":191,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringTwo() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0x33A3", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":true,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":58,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringTree() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0x99C1", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":156,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringErrorHandling() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0x99Z1", String.class);

        String expectedValue = "Not an acceptable hexadecimal. try like 0x123f";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngOne() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-eng-word/113", String.class);

        String expectedValue = "One hundred thirteen";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngTwo() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-eng-word/-0", String.class);

        String expectedValue = "Zero";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngThree() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-eng-word/-85", String.class);

        String expectedValue = "Negative eighty five";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngErrorHandlingInvalidNumber() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-eng-word/-85a", String.class);

        String expectedValue = "not an integer number or length more than 10 digits";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngErrorHandlingTooSmallNumber() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-eng-word/-9999999999", String.class);

        String expectedValue = "less than MIN_VALUE.";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testNumberToWordEngErrorHandlingTooLargeNumber() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/int-to-eng-word/9999999999", String.class);

        String expectedValue = "over MAX_VALUE.";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }
}