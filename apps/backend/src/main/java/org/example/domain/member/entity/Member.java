package org.example.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.common.entity.BaseEntity;

@Table(
        name = "member",
        indexes = {
                @Index(columnList = "email")
        })
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {
    private String email;
    private String password;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Member createMember(String email, String password) {
        return new Member(email, password);
    }

}
