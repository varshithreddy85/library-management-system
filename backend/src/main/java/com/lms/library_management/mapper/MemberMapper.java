package com.lms.library_management.mapper;

import com.lms.library_management.dto.request.MemberRequest;
import com.lms.library_management.dto.response.MemberResponse;
import com.lms.library_management.entity.Member;
import com.lms.library_management.enums.MembershipStatus;

public class MemberMapper {
    public static Member toEntity(MemberRequest request) {
        return Member.builder()
                .name(request.name())
                .email(request.email())
                .status(MembershipStatus.ACTIVE)
                .build();
    }

    public static void updateEntity(Member existingMember, MemberRequest request) {
        existingMember.setName(request.name());
        existingMember.setEmail(request.email());
    }

    public static MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getStatus()
        );
    }
}
