package com.lms.library_management.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateBorrowRequest(
        @NotNull(message = "Book id is required")
        Long bookId,

        @NotNull(message = "Member id is required")
        Long memberId
) {}