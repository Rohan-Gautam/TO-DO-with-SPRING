package com.example.TO_DO.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private boolean completed;
}
