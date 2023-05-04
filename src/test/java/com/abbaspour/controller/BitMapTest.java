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
public class BitMapTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testHexadecimalStringRegularOne() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0xBBF1", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":191,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringRegularTwo() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0x33A3", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":true,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":58,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringRegularThree() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0x99C1", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":156,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringRegularFour() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0x0000", String.class);

        String expectedValue = "{\"machine_on\":false,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":0,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }

    @Test
    public void testHexadecimalStringRegularFive() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0x0101 ", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":16,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }
    @Test
    public void testHexadecimalStringRegularSix() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0xF00f ", String.class);

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":true,\"empty_grounds_fault\":true," +
                "\"water_empty_fault\":true,\"number_of_cups_today\":0,\"descale_required\":true," +
                "\"have_another_one_carl\":true}";
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
    }
    @Test
    public void testHexadecimalStringRegularSeven() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/part-2/0x0ff0", String.class);

        String expectedValue = "{\"machine_on\":false,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":255,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";
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


}