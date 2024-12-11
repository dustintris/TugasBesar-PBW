package com.example.tugasBesar.tracking;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Activity {
    private Long id;

    @NotBlank(message = "User ID tidak boleh kosong")
    private Long userId;

    @NotBlank(message = "Judul tidak boleh kosong")
    private String title;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    private String description;

    @NotBlank(message = "Tanggal aktivitas tidak boleh kosong")
    private Date activityDate;

    @NotBlank(message = "Durasi tidak boleh kosong")
    private Integer duration;  // Duration in minutes

    @NotBlank(message = "Jarak tidak boleh kosong")
    private Float range;  // Distance in kilometers/miles

    private String picture;  // Optional, can be null

    private Date createdAt;
}
