package com.example.testapi.contrloller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created By G900 on 12-Jan-18
 */
@RestController
@RequestMapping(CinemaController.URL)
public class CinemaController {
    public static final String URL = "v1/cinema";



}
