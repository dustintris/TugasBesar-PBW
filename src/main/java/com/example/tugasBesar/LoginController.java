package com.example.tugasBesar;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugasBesar.inputManual.input;
import com.example.tugasBesar.inputManual.inputRepository;
import com.example.tugasBesar.user.User;
import com.example.tugasBesar.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private inputRepository inputRepository;

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
            session.setAttribute("id", user.getId());
            session.setAttribute("role", user.getRole());
            if(session.getAttribute("role").equals("admin")){
                return "redirect:/dashboard-admin"; 
            }else{
                return "redirect:/dashboard"; 
            }
        } else {
            // Log an error message to the console
            System.err.println("Invalid username or password: " + username);
            
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";  
        }
    }

    @GetMapping("/dashboard")
    public String dashboardView(HttpSession session, Model model) {
        // Check if the user is logged in
        if (session.getAttribute("username") == null) {
            return "redirect:/login";  
        }
    
        // Retrieve user information from the session
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
    
        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);
        
        Integer userId = (Integer) session.getAttribute("id");
        if (userId == null) {
            return "redirect:/login"; // Redirect to login if userId is not found
        }
    
        // Retrieve activities for the specific user
        List<input> activities = inputRepository.findByUserId(userId);
    
        // Sort activities by year (descending) and then by month (descending)
        activities.sort(Comparator.comparing(input::getTahun).reversed()
                .thenComparing(Comparator.comparing(input::getBulan).reversed()));
    
        // Add the sorted activities to the model
        model.addAttribute("activities", activities);
    
        // Add month names to the model
        model.addAttribute("monthNames", getMonthNames());
    
        return "dashboard";  
    }

    private String[] getMonthNames() {
        return new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
    }

    @GetMapping("/dashboard-admin")
    public String dashboardAdminView(HttpSession session, Model model) {
        String rolee = "user";
        if (session.getAttribute("id") == null) {
            return "redirect:/login";  
        }else if (session.getAttribute("role").equals(rolee)) {
            return "redirect:/dashboard"; 
        }else{
            String username = (String) session.getAttribute("username");
            model.addAttribute("username", username);
            
            String role = (String) session.getAttribute("role");
            model.addAttribute("role", role);
            
            int id = (int) session.getAttribute("id");
            model.addAttribute("id", id);
            
            return "admin";  
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "index";  
    }
}