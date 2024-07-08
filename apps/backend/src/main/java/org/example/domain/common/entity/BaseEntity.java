package org.example.domain.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.util.TokenGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {
    @Id
    @Column(nullable = false, length = 32)
    private String id;

    @PrePersist
    public void generateId() {
        this.id = TokenGenerator.generateToken();
    }

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @Column(updatable = false, length = 100)
    @CreatedBy
    private String createdBy;

    @Column(nullable = false)
    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime modifiedAt;

    @Column(length = 100)
    @LastModifiedBy
    private String modifiedBy;

    private boolean deleted = false;
}
