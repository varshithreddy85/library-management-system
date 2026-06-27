package com.lms.library_management.service;

import com.lms.library_management.dto.request.CreateBorrowRequest;
import com.lms.library_management.dto.response.BorrowRecordResponse;
import com.lms.library_management.entity.Book;
import com.lms.library_management.entity.BorrowRecord;
import com.lms.library_management.entity.Member;
import com.lms.library_management.enums.BorrowStatus;
import com.lms.library_management.exception.InvalidOperationException;
import com.lms.library_management.exception.ResourceNotFoundException;
import com.lms.library_management.mapper.BorrowRecordMapper;
import com.lms.library_management.repository.BookRepository;
import com.lms.library_management.repository.BorrowRecordRepository;
import com.lms.library_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowRecordService {

    private static final int LOAN_PERIOD_DAYS = 14;

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BorrowRecordResponse borrowBook(CreateBorrowRequest request) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + request.bookId()));

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + request.memberId()));

        if (book.getAvailableCopies() <= 0) {
            throw new InvalidOperationException("No available copies of this book to borrow");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowRecord record = BorrowRecord.builder()
                .book(book)
                .member(member)
                .dueDate(LocalDateTime.now().plusDays(LOAN_PERIOD_DAYS))
                .status(BorrowStatus.BORROWED)
                .build();

        BorrowRecord savedRecord = borrowRecordRepository.save(record);
        return BorrowRecordMapper.toResponse(savedRecord);
    }

    @Transactional
    public BorrowRecordResponse returnBook(Long borrowRecordId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow record not found with id: " + borrowRecordId));

        if (record.getStatus() == BorrowStatus.RETURNED) {
            throw new InvalidOperationException("This book has already been returned");
        }

        record.setReturnDate(LocalDateTime.now());
        record.setStatus(BorrowStatus.RETURNED);

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        BorrowRecord updatedRecord = borrowRecordRepository.save(record);
        return BorrowRecordMapper.toResponse(updatedRecord);
    }

    public List<BorrowRecordResponse> getAllRecords() {
        return borrowRecordRepository.findAll().stream().map(BorrowRecordMapper::toResponse).toList();
    }

    public List<BorrowRecordResponse> getRecordsByMember(Long memberId) {
        return borrowRecordRepository.findByMember_Id(memberId).stream().map(BorrowRecordMapper::toResponse).toList();
    }

    public List<BorrowRecordResponse> getRecordsByBook(Long bookId) {
        return borrowRecordRepository.findByBook_Id(bookId).stream().map(BorrowRecordMapper::toResponse).toList();
    }

    public List<BorrowRecordResponse> getCurrentlyBorrowed() {
        return borrowRecordRepository.findByStatus(BorrowStatus.BORROWED).stream().map(BorrowRecordMapper::toResponse).toList();
    }
}
