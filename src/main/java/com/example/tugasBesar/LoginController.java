package com.example.tugasBesar;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugasBesar.eventAdmin.event;
import com.example.tugasBesar.eventAdmin.eventRepository;
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

    @Autowired
    private eventRepository eventRepository;

    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_USER = "user";

    @GetMapping("/about")
    public String homeView(HttpSession session) {
        return "index";  
    }

    @GetMapping("/login")
    public String loginView(HttpSession session) {
        // Redirect to dashboard if user is already logged in
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
            // Set user information in session
            session.setAttribute("username", user.getUsername());
            session.setAttribute("id", user.getId());
            session.setAttribute("role", user.getRole());

            // Redirect based on user role
            if (ROLE_ADMIN.equals(user.getRole())) {
                return "redirect:/dashboard-admin"; 
            } else {
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
        System.out.println("User ID is "+ userId);
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
    
        // Prepare data for the chart
        int[] weeklyActivityCounts = new int[7]; // Array to hold counts for each day of the week
        Arrays.fill(weeklyActivityCounts, 0); // Initialize counts to 0
    
        // Count activities for each day of the week
        for (input activity : activities) {
            int dayOfWeek = activity.getDayOfWeek(); // Get the day of the week
            if (dayOfWeek >= 0 && dayOfWeek < 7) {
                weeklyActivityCounts[dayOfWeek]++; // Increment the count for the corresponding day
            }
        }
    
        // Add the weekly activity counts to the model
        model.addAttribute("weeklyActivityCounts", weeklyActivityCounts);
    
        return "dashboard";  
    }

    private String[] getMonthNames() {
        return new String[]{
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };
    }

    @GetMapping("/dashboard-admin")
    public String dashboardAdminView(HttpSession session, Model model) {
        // Check if the user is logged in
        Integer userId = (Integer) session.getAttribute("id");
        String role = (String) session.getAttribute("role");
    
        // Redirect to login if user is not logged in
        if (userId == null) {
            return "redirect:/login";  
        }
    
        // Redirect to user dashboard if the role is not admin
        if (ROLE_USER.equals(role)) {
            return "redirect:/dashboard"; 
        }
    
        // At this point, we know the user is logged in and is an admin
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("id", userId);
    
        // Retrieve events for the admin dashboard
        List<event> events = eventRepository.findAll(); // Fetch events
        model.addAttribute("events", events); // Add events to the model
        System.out.println("Model data: " + model.asMap());
        return "admin";  // Return the admin view
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "index";  
    }
}