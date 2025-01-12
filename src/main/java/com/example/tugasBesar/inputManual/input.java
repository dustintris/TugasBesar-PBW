package com.example.tugasBesar.inputManual;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.DayOfWeek;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class input {

    private int idinput;

    @NotNull(message = "Jarak tidak boleh kosong")
    private int jarak;

    @NotNull(message = "Durasi tidak boleh kosong")
    private int durasi;

    @NotNull(message = "Tanggal tidak boleh kosong")
    private int tanggal;

    @NotNull(message = "Bulan tidak boleh kosong")
    private int bulan;

    @NotNull(message = "Tahun tidak boleh kosong")
    private int tahun;

    @NotBlank(message = "Olahraga tidak boleh kosong")
    private String olahraga;

    @NotBlank(message = "Judul tidak boleh kosong")
    private String judul;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    private String deskripsi;

    // Field untuk path gambar
    private String imagePath;

    // Field untuk foto (gambar) yang akan di-upload
    private MultipartFile foto;

    // Konstruktor dengan parameter yang sesuai
    public input(int idinput, int jarak, int durasi, int tanggal, int bulan, int tahun, String olahraga, String judul, String deskripsi, String imagePath) {
        this.idinput = idinput;
        this.jarak = jarak;
        this.durasi = durasi;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.olahraga = olahraga;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.imagePath = imagePath;
    }

    // Method to get the day of the week (0=Sunday, 1=Monday, ..., 6=Saturday)
    public int getDayOfWeek() {
        LocalDate date = LocalDate.of(tahun, bulan, tanggal);
        return date.getDayOfWeek().getValue() % 7; // Convert to 0=Sunday, 1=Monday, ..., 6=Saturday
    }
}