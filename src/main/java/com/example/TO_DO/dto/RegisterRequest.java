package com.example.TO_DO.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(max = 32, min = 6, message = "Password must be at least 6 characters and less than 32 characters")
    private String password;
}
