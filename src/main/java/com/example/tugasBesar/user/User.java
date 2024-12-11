package com.example.tugasBesar.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private int id;
    
    @NotBlank(message = "Email tidak boleh kosong")
    @Size(max = 50, message = "Panjang Email harus kurang dari 50 karakter")
    private String email;

    @NotBlank(message = "Username tidak boleh kosong")
    @Size(min = 4, max = 30, message = "Panjang username harus antara 4 hingga 30 karakter")
    private String username;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 4, max = 60, message = "Panjang password harus antara 4 hingga 60 karakter")
    private String password;

    @NotBlank(message = "Confirm Password tidak boleh kosong")
    @Size(min = 4, max = 60, message = "Panjang password harus antara 4 hingga 60 karakter")
    private String confirmpassword;

    private String role;
}
