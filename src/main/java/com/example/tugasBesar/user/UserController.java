package com.example.tugasBesar.user;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
            System.out.println("PASSWORD NOT THE SAME");
            result.rejectValue(
                "confirmpassword",
                "Password tidak sama",
                "Password invalid (not match)"
            );
        }

        if (result.hasErrors()) {
            result.getAllErrors().forEach(new Consumer<ObjectError>() {
                @Override
                public void accept(ObjectError error) {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        System.out.println("Field: " + fieldError.getField());
                        System.out.println("Rejected Value: " + fieldError.getRejectedValue());
                        System.out.println("Error Message: " + fieldError.getDefaultMessage());
                    } else {
                        System.out.println("Object Error: " + error.getDefaultMessage());
                    }
                }
            });
            return "register";  // Kembali ke form register dengan error
        }

        boolean registered = userService.register(user);
        if(registered){
            System.out.println("Model attribute set: " + "successMessage, User successfully registered with username:" + user.getUsername());
            model.addAttribute("successMessage", "User successfully registered with username: " + user.getUsername());
            return "redirect:/login";
        }else{
            System.out.println("Model attribute set: " + "ErrorBrok: Registration Error");
            model.addAttribute("ErrorBrok", "Registration Error");
            return "register";
        }
    }
}
