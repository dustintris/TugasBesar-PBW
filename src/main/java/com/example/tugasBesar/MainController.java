package com.example.tugasBesar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "Home";
    }

    @RequestMapping("/home")
    public String home() {
        return "Home";
    }

    @RequestMapping("/login")
    public String login() {
        return "Login";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "Signup";
    }

    @RequestMapping("/user")
    public String User() {
        return "User";
    }
}
