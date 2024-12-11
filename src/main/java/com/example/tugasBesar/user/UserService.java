package com.example.tugasBesar.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder PE;

    public boolean register(User user) {
        try {
            user.setPassword(PE.encode(user.getPassword()));

            userRepository.save(user);
            System.out.println("User successfully saved with username: " + user.getUsername());
            return true;
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    public User login(String username, String password) {
        // Cari user berdasarkan username
        Optional<User> userOptional = userRepository.findByUsername(username);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Verifikasi password dengan yang tersimpan di database
            boolean passwordMatches = PE.matches(password, user.getPassword());
            
            if (passwordMatches) {
                return user;  // User ditemukan dan password cocok
            }
        }
        return null;  // Username atau password salah
    }
    
}
