package com.abbaspour.utils;

import com.abbaspour.model.BitMap;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @author Mehdi Abbaspour
 * @version 1.0
 * created: May 3, 2023
 */
@Service
public class Decode {
    /**
     * Description: static function for generating BitMap class according to Pattern.
     * input: hexString
     * output: class BitMap
     * When input is wrong, null in returned.
     * Hex string express
     * bits property
     * 1 machine_on
     * 2 grinding_beans
     * 3 empty_grounds_fault
     * 4 water_empty_fault
     * 5 - 12 number_of_cups_today number
     * 15 descale_required
     * 14,16 have_another_one_carl
     * @param hexString
     * @return BitMap class
     */
    public static BitMap generateBitMapFromPattern (String hexString){
        hexString=hexString.trim();
        String regex = "^0[xX][0-9A-Fa-f]{4}$";

        boolean isMatch = hexString.matches(regex);
        if (!isMatch)  // in case of error
            return null;

        String bits=hexToBitString(hexString);
        BitMap bitMap = new BitMap();
        if (bits.length()<16) // in case of zerro in upper left
            bits+="0".repeat(16);

        bitMap.setMachine_on(bits.charAt(0)=='1'); //1 machine_on

        bitMap.setGrinding_beans(bits.charAt(1)=='1');//2 grinding_beans

        bitMap.setEmpty_grounds_fault(bits.charAt(2)=='1'); //3 empty_grounds_fault

        bitMap.setWater_empty_fault(bits.charAt(3)=='1'); //4 water_empty_fault

        int j = bits.charAt(4)=='1' ? 1 : 0; //5 - 12 number_of_cups_today
        for (int i = 6; i <= 12; i++) {
            j += bits.charAt(i - 1)=='1' ? Math.pow(2, (i - 5)) : 0;
        }
        bitMap.setNumber_of_cups_today(j);

        bitMap.setDescale_required(bits.charAt(14)=='1'); //15 descale_required bool
        bitMap.setHave_another_one_carl (bits.charAt(13)=='1' || bits.charAt(15)=='1'); //14,16 have_another_one_carl bool
        return bitMap;

    }
    private static String hexToBitString(String hex) {
        byte[] bytes = new BigInteger(hex.substring(2), 16).toByteArray();
        String sb = "";
        for (byte b : bytes) {
            for (int i = 7; i >= 0; i--) {
                sb=((b & (1 << i)) == 0 ? "0" : "1")+sb;
            }
        }
        return sb;
    }

}
