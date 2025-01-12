package com.example.tugasBesar.eventAdmin;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class event {

    
    private int idlomba;

    @NotBlank(message = "Title tidak boleh kosong")
    private String title;

    @NotNull(message = "Tanggal tidak boleh kosong")
    private int tanggal;

    @NotNull(message = "Bulan tidak boleh kosong")
    private int bulan;

    @NotNull(message = "Tahun tidak boleh kosong")
    private int tahun;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    private String description;

    @NotBlank(message = "Lokasi tidak boleh kosong")
    private String location;

    @NotNull(message = "Range tidak boleh kosong")
    private int range;

    


    
}