package com.lms.library_management.service;

import com.lms.library_management.dto.request.CreateBookRequest;
import com.lms.library_management.dto.request.UpdateBookRequest;
import com.lms.library_management.dto.response.BookResponse;
import com.lms.library_management.entity.Book;
import com.lms.library_management.exception.DuplicateResourceException;
import com.lms.library_management.exception.InvalidOperationException;
import com.lms.library_management.exception.ResourceNotFoundException;
import com.lms.library_management.mapper.BookMapper;
import com.lms.library_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse createBook(CreateBookRequest request) {
        if (bookRepository.existsByIsbn(request.isbn())) {
            throw new DuplicateResourceException("Book with ISBN " + request.isbn() + " already exists");
        }
        Book book = BookMapper.toEntity(request);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toResponse(savedBook);
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return BookMapper.toResponse(book);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toResponse).toList();
    }

    public BookResponse updateBook(Long id, UpdateBookRequest request) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        int copiesDifference = request.totalCopies() - existingBook.getTotalCopies();
        int newAvailableCopies = existingBook.getAvailableCopies() + copiesDifference;

        if (newAvailableCopies < 0) {
            throw new InvalidOperationException("Cannot reduce total copies below the number currently borrowed");
        }

        BookMapper.updateEntity(existingBook, request);
        existingBook.setAvailableCopies(newAvailableCopies);

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.toResponse(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<BookResponse> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream().map(BookMapper::toResponse).toList();
    }

    public List<BookResponse> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author).stream().map(BookMapper::toResponse).toList();
    }
}
