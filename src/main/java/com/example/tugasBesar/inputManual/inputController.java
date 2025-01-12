package com.example.tugasBesar.inputManual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class inputController {

    @Autowired
    private inputRepository inputRepository;

    private static final String IMAGE_UPLOAD_DIR = "C:/Users/LENOVO/Documents/00UAS PBW/TugasBesar-PBW/uploads/images/test/";

    // Endpoint untuk menampilkan halaman input manual
    @GetMapping("/manual")
    public String showManualPage() {
        return "manual";  // Ganti dengan nama halaman yang sesuai
    }

    // Endpoint untuk menangani submit data input dan upload gambar
    @PostMapping("/api/input/manual")
    public String submitInput(@Valid @ModelAttribute input input, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "manual";  // Kembali ke halaman input jika ada error
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
                model.addAttribute("errorMessage", "Terjadi kesalahan saat menyimpan gambar."+ e.getMessage());
                return "manual";  // Kembali ke halaman input jika ada error saat menyimpan gambar
            }
        }

        // Menyimpan data input beserta path gambar ke database
        inputRepository.save(input);

        // Menambahkan pesan sukses ke model dan mengarahkan ke halaman yang sesuai
        model.addAttribute("message", "Data berhasil disimpan!");
        return "manual";  // Bisa diarahkan ke halaman sukses setelah data berhasil disimpan
    }
}
