package com.abbaspour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author Mehdi Abbaspour
 * @version 1.0
 * created: May 1, 2023
 *
 * First page showing home.html    ....getMetod at root
 */
@Controller
public class HomeController {

    @GetMapping()
    public String welcome() {
        return "home";
    }
}