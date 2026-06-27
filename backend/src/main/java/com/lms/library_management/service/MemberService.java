package com.lms.library_management.service;

import com.lms.library_management.dto.request.MemberRequest;
import com.lms.library_management.dto.response.MemberResponse;
import com.lms.library_management.entity.Member;
import com.lms.library_management.exception.DuplicateResourceException;
import com.lms.library_management.exception.ResourceNotFoundException;
import com.lms.library_management.mapper.MemberMapper;
import com.lms.library_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse createMember(MemberRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Member with email " + request.email() + " already exists");
        }
        Member member = MemberMapper.toEntity(request);
        Member savedMember = memberRepository.save(member);
        return MemberMapper.toResponse(savedMember);
    }

    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        return MemberMapper.toResponse(member);
    }

    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream().map(MemberMapper::toResponse).toList();
    }

    public MemberResponse updateMember(Long id, MemberRequest request) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));

        boolean emailChanged = !existingMember.getEmail().equalsIgnoreCase(request.email());
        if (emailChanged && memberRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Member with email " + request.email() + " already exists");
        }

        MemberMapper.updateEntity(existingMember, request);
        Member updatedMember = memberRepository.save(existingMember);
        return MemberMapper.toResponse(updatedMember);
    }

    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
