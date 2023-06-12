package com.example.skeduler.services;

import com.example.skeduler.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    JwtService jwtService;

    @Test
    void 토큰_유효성_검사() {
        Member member1 = new Member((long) 1, "user1", "pass", "user1@gmail.com", "user123");
        Member member2 = new Member((long) 2, "user2", "pass", "user2@gmail.com", "user234");
        String token = jwtService.generateAccessToken(member1);

        assertThat(jwtService.isTokenValid(token, member1)).isEqualTo(true);
        assertThat(jwtService.isTokenValid(token, member2)).isEqualTo(false);

    }
}