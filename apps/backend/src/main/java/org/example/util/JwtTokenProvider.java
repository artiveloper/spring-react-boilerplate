package org.example.util;

import io.jsonwebtoken.Jwts;
import org.example.domain.auth.dto.MemberPrincipal;
import org.example.domain.member.entity.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String MEMBER_ID_CLAIM = "MEMBER_ID";
    private final String EMAIL_CLAIM = "EMAIL";

    private final SecretKey secretKey;

    public JwtTokenProvider(
            @Value("${spring.jwt.secret}") String secret
    ) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String createToken(MemberPrincipal memberPrincipal, Long expiredMs) {
        Member member = memberPrincipal.getMember();

        return Jwts.builder()
                .claim(MEMBER_ID_CLAIM, member.getId())
                .claim(EMAIL_CLAIM, member.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public String getMemberId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(MEMBER_ID_CLAIM, String.class);
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(EMAIL_CLAIM, String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
}
