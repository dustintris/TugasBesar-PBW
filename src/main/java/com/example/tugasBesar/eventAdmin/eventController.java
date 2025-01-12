package com.example.tugasBesar.eventAdmin;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class eventController {

    @Autowired
    private eventRepository eventRepository;

    // Endpoint untuk menampilkan halaman input manual
    @GetMapping("/eventAdmin")
    public String showManualPage() {
        return "eventAdmin";  // Ganti dengan nama halaman yang sesuai
    }

    // Endpoint untuk menangani submit data input dan upload gambar
    @PostMapping("/api/input/eventAdmin")
    public String submitInput(@Valid @ModelAttribute event event, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "eventAdmin";  // Kembali ke halaman input jika ada error
        }

        


        // Menyimpan data input beserta path gambar ke database
        eventRepository.save(event, session); // Ensure this method signature matches your repository

        // Menambahkan pesan sukses ke model dan mengarahkan ke halaman yang sesuai
        model.addAttribute("message", "Data berhasil disimpan!");
        return "eventAdmin";  // Bisa diarahkan ke halaman sukses setelah data berhasil disimpan
    }
}
