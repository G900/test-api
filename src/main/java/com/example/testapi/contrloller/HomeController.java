package com.example.testapi.contrloller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By G900 on 14-Jan-18
 */
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
