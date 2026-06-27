package com.lms.library_management.repository;

import com.lms.library_management.entity.Member;
import com.lms.library_management.enums.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);

    List<Member> findByName(String name);
    List<Member> findByStatus(MembershipStatus status);
}