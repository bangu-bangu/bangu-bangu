package com.github.bbooong.bangumall.auth.application;

import com.github.bbooong.bangumall.auth.application.dto.MemberCreateRequest;
import com.github.bbooong.bangumall.auth.domain.Member;
import com.github.bbooong.bangumall.auth.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long createMember(final MemberCreateRequest request) {
        final Member member = Member.create(request.email(), request.password());
        return memberRepository.save(member).getId();
    }
}
