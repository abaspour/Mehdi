package com.abbaspour.controller;

import com.abbaspour.model.NumberToEngWordConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "api/int-to-eng-word")
public class EngInt2WordController {
    @CrossOrigin
    @GetMapping("/{number}")
    public ResponseEntity<String> int2EnglishWord(@PathVariable String number) {
        try {
            String regex = "^-?\\d{1,9}$";
            if (!number.matches(regex))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not an integer number");

            Long intNumber = Long.valueOf(number);

            if (intNumber > Integer.MAX_VALUE)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("over MAX_VALUE.");
            else if (intNumber < Integer.MIN_VALUE)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("less than MIN_VALUE.");

            String word = NumberToEngWordConverter.convert(intNumber.intValue());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(word.substring(0, 1).toUpperCase() + word.substring(1));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Error occured.");
        }

    }

}

