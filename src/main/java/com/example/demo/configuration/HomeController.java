package com.example.demo.configuration;


import org.springframework.web.bind.annotation.RequestMapping;



public class HomeController {

	@RequestMapping("/")

    public String index() {
        return "redirect:swagger-ui.html";
    }
}
