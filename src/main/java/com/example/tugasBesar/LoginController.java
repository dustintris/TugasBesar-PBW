package com.example.tugasBesar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugasBesar.user.User;
import com.example.tugasBesar.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginView(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/dashboard";  
        }
        return "login";  
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, 
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        
        User user = userService.login(username, password);
        
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            return "redirect:/dashboard";  
        } else {
            // Log an error message to the console
            System.err.println("Invalid username or password: " + username);
            
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";  
        }
    }

    @GetMapping("/dashboard")
    public String dashboardView(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";  
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "dashboard";  
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "index";  
    }
}