package com.abbaspour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author Mehdi Abbaspour
 * @version 1.0
 * created: May 1, 2023
 */
@Controller
public class HomeController {

    @GetMapping()
    public String welcome() {
        return "home";
    }
}