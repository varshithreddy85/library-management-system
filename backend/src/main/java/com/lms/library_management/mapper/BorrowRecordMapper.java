package com.lms.library_management.mapper;

import com.lms.library_management.dto.response.BorrowRecordResponse;
import com.lms.library_management.entity.BorrowRecord;

public class BorrowRecordMapper {
    public static BorrowRecordResponse toResponse(BorrowRecord record) {
        return new BorrowRecordResponse(
                record.getId(),
                record.getBook().getId(),
                record.getBook().getTitle(),
                record.getMember().getId(),
                record.getMember().getName(),
                record.getIssueDate(),
                record.getDueDate(),
                record.getReturnDate(),
                record.getStatus()
        );
    }
}
