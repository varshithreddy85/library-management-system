package com.lms.library_management.mapper;

import com.lms.library_management.dto.request.CreateBookRequest;
import com.lms.library_management.dto.request.UpdateBookRequest;
import com.lms.library_management.dto.response.BookResponse;
import com.lms.library_management.entity.Book;

public class BookMapper {
    public static Book toEntity(CreateBookRequest request) {
        return Book.builder()
                .title(request.title())
                .author(request.author())
                .isbn(request.isbn())
                .totalCopies(request.totalCopies())
                .availableCopies(request.totalCopies())
                .build();
    }

    public static void updateEntity(Book existingBook, UpdateBookRequest request) {
        existingBook.setTitle(request.title());
        existingBook.setAuthor(request.author());
        existingBook.setTotalCopies(request.totalCopies());
    }

    public static BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }
}
