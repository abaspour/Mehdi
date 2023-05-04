package com.abbaspour.utils;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

public class DecodeTest {

    @Test
    public void machineCarl() {
        String code = Decode.generateBitMapFromPattern("0xBBF1").toString();

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":191,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        assertEquals(expectedValue, code);
    }

    @Test
    public void machineBeansCarl() {
        String code = Decode.generateBitMapFromPattern("0x33A3").toString();

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":true,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":58,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";
        
        assertEquals(expectedValue, code);
    }

    @Test
    public void machineCarlNumber() {
        String code = Decode.generateBitMapFromPattern("0x99C1").toString();

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":156,\"descale_required\":false," +
                "\"have_another_one_carl\":true}";

        assertEquals(expectedValue, code);
    }

    @Test
    public void zero() {
        String code = Decode.generateBitMapFromPattern("0x0000").toString();

        String expectedValue = "{\"machine_on\":false,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":0,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";

        assertEquals(expectedValue, code);
    }

    @Test
    public void machine() {
        String code = Decode.generateBitMapFromPattern("0x0101").toString();

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":16,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";

        assertEquals(expectedValue, code);
    }
    @Test
    public void allTrueZero() {
        String code = Decode.generateBitMapFromPattern("0xF00f").toString();

        String expectedValue = "{\"machine_on\":true,\"grinding_beans\":true,\"empty_grounds_fault\":true," +
                "\"water_empty_fault\":true,\"number_of_cups_today\":0,\"descale_required\":true," +
                "\"have_another_one_carl\":true}";

        assertEquals(expectedValue, code);
    }
    @Test
    public void allFalse() {
        String code = Decode.generateBitMapFromPattern("0x0ff0").toString();

        String expectedValue = "{\"machine_on\":false,\"grinding_beans\":false,\"empty_grounds_fault\":false," +
                "\"water_empty_fault\":false,\"number_of_cups_today\":255,\"descale_required\":false," +
                "\"have_another_one_carl\":false}";

        assertEquals(expectedValue, code);
    }
}
