package com.abbaspour.controller;
import com.abbaspour.service.IntegerToWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mehdi Abbaspour
 * @version 1.2
 * created: May 1, 2023
 * updated: May 3, 2023
 */
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "api/int-to-word")
public class Int2WordController {
    @Autowired
    IntegerToWordService integerToWordService;
    @CrossOrigin
    @GetMapping("/{language}/{number}")
    public ResponseEntity<String> int2Word(@PathVariable String language, @PathVariable String number) {
        try {
            number=number.replaceAll("\\s+", "");
            String regex = "^-?\\d{1,10}$";
            if (!number.matches(regex))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not an integer number or length more than 10 digits");

            Long intNumber = Long.valueOf(number);

            if (intNumber > Integer.MAX_VALUE)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("over MAX_VALUE.");
            else if (intNumber < Integer.MIN_VALUE)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("less than MIN_VALUE.");

            String word="";
            if (language.equalsIgnoreCase("English"))
                word = integerToWordService.toEnglishWordConverter(intNumber);
            else if (language.equalsIgnoreCase("French"))
                word = integerToWordService.toFrenchWordConverter(intNumber);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Language not supported.");

            return ResponseEntity.status(HttpStatus.OK).body(word);
        } catch (NumberFormatException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not an integer number");
        } catch (ArithmeticException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number too small or too big.");
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Error occurred.");
        }
    }
}
