/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.tugasBesar.eventParticipant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tugasBesar.eventAdmin.event;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class participantController {

    @Autowired
    private epRepo eRepo;

    // Endpoint untuk menangani submit data input dan upload gambar
    @PostMapping("/api/event/registration")
    public String registration(@Valid @ModelAttribute eventParticipants event, 
                            BindingResult bindingResult, 
                            RedirectAttributes redirectAttributes, 
                            HttpSession session) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Add errors to redirect attributes
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/eventUser";  // Redirect back to the admin page
        }

        // Attempt to save the eventParticipants
        boolean isSaved = eRepo.save(event, session); // Ensure this method signature matches your repository

        // Check if the save was successful
        if (isSaved) {
            redirectAttributes.addFlashAttribute("message", "Data berhasil disimpan!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal menyimpan data. Silakan coba lagi.");
        }

        return "redirect:/eventUser";  // Redirect to the admin page
    }
    
}
