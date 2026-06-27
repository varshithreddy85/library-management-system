package com.lms.library_management.controller;

import com.lms.library_management.dto.request.CreateBorrowRequest;
import com.lms.library_management.dto.response.BorrowRecordResponse;
import com.lms.library_management.service.BorrowRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/borrow-records")
@RequiredArgsConstructor
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    @PostMapping
    public ResponseEntity<BorrowRecordResponse> borrowBook(@Valid @RequestBody CreateBorrowRequest request) {
        return new ResponseEntity<>(borrowRecordService.borrowBook(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowRecordResponse> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowRecordService.returnBook(id));
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecordResponse>> getAllRecords() {
        return ResponseEntity.ok(borrowRecordService.getAllRecords());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BorrowRecordResponse>> getRecordsByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(borrowRecordService.getRecordsByMember(memberId));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowRecordResponse>> getRecordsByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(borrowRecordService.getRecordsByBook(bookId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<BorrowRecordResponse>> getCurrentlyBorrowed() {
        return ResponseEntity.ok(borrowRecordService.getCurrentlyBorrowed());
    }
}