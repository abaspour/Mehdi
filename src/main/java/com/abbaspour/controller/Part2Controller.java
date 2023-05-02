package com.abbaspour.controller;


import com.abbaspour.model.BitMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "api/part-2")
public class Part2Controller {

    @CrossOrigin
    @GetMapping("/{hexString}")
    public ResponseEntity<String> decode(@PathVariable String hexString) {
        String json = "";
        try {
            hexString=hexString.trim();
            String regex = "^0[xX][0-9A-Fa-f]{4}$";
            boolean isMatch = hexString.matches(regex);
            if (!isMatch)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not an acceptable hexadecimal. try like 0x123f");

            String bits=hexToBitString(hexString);
//            System.out.println(bits);
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

            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(bitMap);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Error occured.");
        }

    }
    public static String hexToBitString(String hex) {
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
