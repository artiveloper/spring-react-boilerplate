package org.example.util;

import java.util.UUID;

public class TokenGenerator {

    /**
     * 토큰생성 auto increment 와 성능이 유사하다.
     * 참고 URL : https://medium.com/aha-official/%EC%95%84%ED%95%98-rest-api-%EC%84%9C%EB%B2%84-%EA%B0%9C%EB%B0%9C-6-43568d94878a
     */
    public static String generateToken() {
        String[] tokens = UUID.randomUUID().toString().split("-");
        return tokens[2] + tokens[1] + tokens[0] + tokens[3] + tokens[4];
    }

    /**
     * 6자리의 숫자 코드를 생성한다.
     */
    public static String generateAuthCode() {
        int randomCode = (int) (Math.random() * 899999) + 100000;
        return String.valueOf(randomCode);
    }

}
