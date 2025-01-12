package com.example.tugasBesar.tracking;

import org.springframework.security.web.webauthn.api.Bytes;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Activity {
    private int id;

    @NotBlank(message = "User ID tidak boleh kosong")
    private int userId;

    @NotBlank(message = "Judul tidak boleh kosong")
    private String title;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    private String deskripsi;

    @NotBlank(message = "Durasi tidak boleh kosong")
    private int duration;  // Duration in minutes

    @NotBlank(message = "Jarak tidak boleh kosong")
    private int jarak;  // Distance in kilometers/miles

    @NotBlank(message = "Jarak tidak boleh kosong")
    private Bytes image;  // Optional, can be null

    private int date;

    private int month;

    private int year;

    Activity(int aInt, int aInt0, String string, String string0, int aInt1, int aInt2, byte[] imageBytes, int aInt3, int aInt4, int aInt5) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
