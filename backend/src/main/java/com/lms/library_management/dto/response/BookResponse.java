package com.lms.library_management.dto.response;

public record BookResponse(
        Long id,
        String title,
        String author,
        String isbn,
        Integer totalCopies,
        Integer availableCopies
) {}