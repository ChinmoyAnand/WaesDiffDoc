package com.chinmoy.diffText.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API for Swagger API documentation.
 */
@Controller
public class HomeController {

    /**
     * Endpoint that redirects the user to the Swagger API documentation.
     *
     * @return Spring redirect command to the swagger ui page.
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }

}

