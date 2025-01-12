package com.example.tugasBesar.kegiatan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class kegiatanController {

    @Autowired
    private com.example.tugasBesar.kegiatan.kegiatanRepository kegiatanRepository;

    // Endpoint untuk menampilkan data berdasarkan username
    @GetMapping("/kegiatan")
    public String showKegiatanByUsername(@RequestParam(required = false) String username, Model model) {
    List<kegiatan> inputData;

    inputData = kegiatanRepository.findByUsername(username);

    

    // Menambahkan data kegiatan ke model
    model.addAttribute("inputData", inputData);
    return "kegiatan"; // Mengembalikan nama file HTML
}

}