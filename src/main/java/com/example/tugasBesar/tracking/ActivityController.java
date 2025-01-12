package com.example.tugasBesar.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("/activities/add")
    public String addActivity(@Valid @ModelAttribute("activity") Activity activity, BindingResult result, Model model, MultipartFile image) {
        if (result.hasErrors()) {
            return "dashboard";  // Return to the form if there are validation errors
        }

        boolean success = activityService.saveActivity(activity);
        if (success) {
            model.addAttribute("successMessage", "Activity added successfully.");
            return "dashboard";  // Redirect to the activity list
        } else {
            model.addAttribute("errorMessage", "Failed to add activity.");
            return "dashboard"; // Return to the form with error
        }
    }
}