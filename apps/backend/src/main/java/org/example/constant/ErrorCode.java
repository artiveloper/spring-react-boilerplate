package org.example.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /** 공통 에러 **/
    COMMON_SYSTEM_ERROR("C001", "일시적인 오류가 발생했어요. 잠시 후 다시 시도해주세요."), // 장애 상황
    COMMON_INVALID_PARAMETER("C002", "요청한 값이 올바르지 않아요."),
    COMMON_ENTITY_NOT_FOUND("C003", "존재하지 않는 엔티티에요."),
    COMMON_ILLEGAL_STATUS("C004", "잘못된 상태값이에요."),

    /** 멤버 관련 에러 코드 **/
    MEMBER_EXIST("M100", "이미 사용중인 이메일이에요."),

    /** 시큐리티 관련 에러 코드 **/
    SECURITY_AUTHENTICATION_FAIL("S900", "이메일 또는 비밀번호를 다시 확인해주세요."),
    SECURITY_ACCESS_DENY("S901", "허용되지 않은 접근이에요."),
    SECURITY_ACCESS_TOKEN_EXPIRED("S902", "토큰이 만료되었어요."),
    SECURITY_JWT_ERROR("S903", "토큰이 유효하지 않아요."),

    ;

    private final String code;
    private final String message;

}
