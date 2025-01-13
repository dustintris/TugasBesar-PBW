package com.example.tugasBesar.inputManual;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class inputController {

    @Autowired
    private inputRepository inputRepository;

    private static final String IMAGE_UPLOAD_DIR = "C:\\Users\\Dusti\\Documents\\GitHub\\TugasBesar-PBW\\src\\main\\resources\\static\\Images\\";

    // Endpoint untuk menampilkan halaman input manual
    @GetMapping("/activity/{id}")
    public String showActivityDetails(@PathVariable("id") int id, Model model) {
        // Fetch the activity by ID from the repository
        input activity = inputRepository.findById(id); // Ensure you have this method in your repository
    
        if (activity == null) {
            return "redirect:/dashboard"; // Redirect if the activity is not found
        }
    
        model.addAttribute("activity", activity); // Add the activity to the model
        return "activity-details"; // Return the name of the new HTML template
    }
    
    // Endpoint untuk menangani submit data input dan upload gambar
    @PostMapping("/api/input/manual")
    public String submitInput(@Valid @ModelAttribute input input, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/dashboard";  // Kembali ke halaman input jika ada error
        }

        // Menangani file gambar
        MultipartFile foto = input.getFoto();  // Ambil file gambar dari objek input
        if (foto != null && !foto.isEmpty()) {
            try {
                String fileName = foto.getOriginalFilename();
                String filePath = IMAGE_UPLOAD_DIR + fileName;
                File imageFile = new File(filePath);

                // Membuat direktori jika belum ada
                if (!imageFile.getParentFile().exists()) {
                    imageFile.getParentFile().mkdirs();
                }

                // Menyimpan file gambar ke direktori
                foto.transferTo(imageFile);

                // Menyimpan path gambar ke dalam objek input
                input.setImagePath(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Terjadi kesalahan saat menyimpan gambar: " + e.getMessage());
                return "redirect:/dashboard";  // Kembali ke halaman input jika ada error saat menyimpan gambar
            }
        }

        // Retrieve user ID from session if needed
        Integer userId = (Integer) session.getAttribute("id");

        // Menyimpan data input beserta path gambar ke database
        inputRepository.save(input, session); // Ensure this method signature matches your repository

        // Menambahkan pesan sukses ke model dan mengarahkan ke halaman yang sesuai
        model.addAttribute("message", "Data berhasil disimpan!");
        return "redirect:/dashboard";  // Bisa diarahkan ke halaman sukses setelah data berhasil disimpan
    }
}