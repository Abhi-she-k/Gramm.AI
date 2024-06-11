package com.example.Gramm.ai.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage {

    @GetMapping("/")
    public String home(Model model) {
        return "index"; // This will resolve to a Thymeleaf template named 'index.html'
    }
}
