package com.lms.library_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateBookRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Author is required")
        String author,

        @NotNull(message = "Total copies is required")
        @Positive(message = "Total copies must be greater than 0")
        Integer totalCopies
) {}
