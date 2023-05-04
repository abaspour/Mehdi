package com.abbaspour.controller;


import com.abbaspour.model.BitMap;
import com.abbaspour.service.DecodeService;
import com.abbaspour.utils.Decode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mehdi Abbaspour
 * @version 1.2
 * created: May 1, 2023
 * updated: May 3, 2023
 * Description: Rest Controller for api/part-2/{hexString} request returning json response according to bit map table
 * bits property Type
 * 1 machine_on bool
 * 2 grinding_beans bool
 * 3 empty_grounds_fault bool
 * 4 water_empty_fault bool
 * 5 - 12 number_of_cups_today number
 * 15 descale_required bool
 * 14,16 have_another_one_carl bool
 *
 * Sample:
 * request: api/part-2/0xBBF1
 * response: {"machine_on": true, "grinding_beans": false, "empty_grounds_fault": false,
 * "water_empty_fault": false, "number_of_cups_today": 191, "descale_required": false,
 * "have_another_one_carl": true}
 */
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "api/decode")
public class DecodeController {
    @Autowired
    DecodeService decodeService;
    @CrossOrigin
    @GetMapping("/{hexString}")
    public ResponseEntity<String> decode(@PathVariable String hexString) {
        String json = "";
        try {

            BitMap bitMap= decodeService.decode(hexString);
            if (bitMap == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not an acceptable hexadecimal. try like 0x123f");

            return ResponseEntity.status(HttpStatus.OK).body(bitMap.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Error occured.");
        }

    }

}
