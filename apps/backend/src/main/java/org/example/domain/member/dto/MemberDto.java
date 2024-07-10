package org.example.domain.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.domain.member.entity.Member;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(access = AccessLevel.PROTECTED)
@Data
public class MemberDto {
    private String memberId;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();
    }
}
