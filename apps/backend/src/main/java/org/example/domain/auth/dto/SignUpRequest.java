package org.example.domain.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SignUpRequest {
    private String email;
    private String password;
}
