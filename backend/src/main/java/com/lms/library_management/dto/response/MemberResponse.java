package com.lms.library_management.dto.response;

import com.lms.library_management.enums.MembershipStatus;

public record MemberResponse(
        Long id,
        String name,
        String email,
        MembershipStatus status
) {
}
