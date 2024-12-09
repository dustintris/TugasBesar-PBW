package com.example.tugasBesar.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerView() {
        return "register";
    }

    @GetMapping("/results")
    public String resultsView() {
        return "results";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, Model model) {
        System.out.println("Attempt to register with username: " + user.getUsername() + " and password: " + user.getPassword());

        if (!user.getPassword().equals(user.getConfirmpassword())) {
            result.rejectValue(
                "confirmpassword",
                "Password tidak sama",
                "Password invalid (not match)"
            );
        }

        if (result.hasErrors()) {
            return "register";  // Kembali ke form register dengan error
        }

        boolean registered = userService.register(user);
        if(registered){
            System.out.println("Model attribute set: " + "successMessage, User successfully registered with username:" + user.getUsername());
            model.addAttribute("successMessage", "User successfully registered with username: " + user.getUsername());
            return "redirect:/results";
        }else{
            System.out.println("Model attribute set: " + "ErrorBrok: Registration Error");
            model.addAttribute("ErrorBrok", "Registration Error");
            return "register";
        }
    }
}
