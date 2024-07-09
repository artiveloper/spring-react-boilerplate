package org.example.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.auth.dto.SignUpRequest;
import org.example.common.dto.CommonResponse;
import org.example.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
        memberService.createMember(signUpRequest);

        return CommonResponse.success();
    }

}
