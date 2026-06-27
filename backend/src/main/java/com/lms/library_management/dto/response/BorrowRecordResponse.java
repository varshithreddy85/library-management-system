package com.lms.library_management.dto.response;

import com.lms.library_management.enums.BorrowStatus;

import java.time.LocalDateTime;

public record BorrowRecordResponse(
        Long id,
        Long bookId,
        String bookTitle,
        Long memberId,
        String memberName,
        LocalDateTime issueDate,
        LocalDateTime dueDate,
        LocalDateTime returnDate,
        BorrowStatus status
) {}