package org.example.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.constant.ErrorCode;
import org.example.domain.auth.dto.SignUpRequest;
import org.example.domain.member.entity.Member;
import org.example.domain.member.reposigory.MemberRepository;
import org.example.exception.BaseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(SignUpRequest signUpRequest) {
        String email = signUpRequest.getEmail();

        // 이메일 중복 가입 확인
        memberRepository.findByEmail(email).ifPresent(member -> {
            throw new BaseException(ErrorCode.MEMBER_EXIST);
        });

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Member member = Member.createMember(email, encodedPassword);

        memberRepository.save(member);
    }

}
