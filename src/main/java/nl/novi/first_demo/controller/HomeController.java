package nl.novi.first_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "hello")
    public String home (){
        return "Hello World!";
    }

}
