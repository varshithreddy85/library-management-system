package com.lms.library_management.repository;

import com.lms.library_management.entity.BorrowRecord;
import com.lms.library_management.enums.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByBook_Id(Long bookId);
    List<BorrowRecord> findByMember_Id(Long memberId);
    List<BorrowRecord> findByStatus(BorrowStatus status);

    List<BorrowRecord> findByBook_IdAndMember_IdAndStatus(Long bookId, Long memberId, BorrowStatus status);
}
