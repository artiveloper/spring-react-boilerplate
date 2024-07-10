package org.example.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.common.dto.CommonResponse;
import org.example.constant.ErrorCode;
import org.example.domain.auth.dto.MemberPrincipal;
import org.example.domain.auth.dto.SignInRequest;
import org.example.domain.auth.dto.SignInResponse;
import org.example.domain.auth.dto.SignUpRequest;
import org.example.domain.member.service.MemberService;
import org.example.util.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
        memberService.createMember(signUpRequest);

        return CommonResponse.success();
    }

    @PostMapping("/sign-in")
    public CommonResponse<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getEmail(),
                            signInRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            MemberPrincipal memberPrincipal = (MemberPrincipal) authentication.getPrincipal();

            String accessToken = jwtTokenProvider.createToken(memberPrincipal, 60 * 60 * 10L);
            SignInResponse signInResponse = new SignInResponse(accessToken);

            return CommonResponse.success(signInResponse, "로그인을 성공했어요.");
        } catch (Exception e) {
            return CommonResponse.fail(ErrorCode.SECURITY_AUTHENTICATION_FAIL);
        }
    }

}
