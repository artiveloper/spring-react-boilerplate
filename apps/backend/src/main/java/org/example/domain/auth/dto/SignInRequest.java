package org.example.domain.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SignInRequest {
    private String email;
    private String password;
}
