package com.example.testapi.contrloller;

/**
 * Created By G900 on 15-Jan-18
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController
{

    @RequestMapping("/")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }

}
