package com.example.skeduler.services;

import com.example.skeduler.dto.AuthenticationRequestDto;
import com.example.skeduler.dto.AuthenticationResponseDto;
import com.example.skeduler.dto.RegisterRequestDto;
import com.example.skeduler.repositories.MemberRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationServiceTest {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    JwtService jwtService;

    private String token;

    @BeforeAll
    public void beforeAll() {
        RegisterRequestDto dto = new RegisterRequestDto("sangyoon", "sy@gmail.com", "sypass", "sangyoon");
        AuthenticationResponseDto a = authenticationService.signup(dto);
        token = a.token();
    }

    @Test
    void 회원가입() {
        RegisterRequestDto dto = new RegisterRequestDto("donghyun", "dh@gmail.com", "dhpass", "dooomhyun");
        AuthenticationResponseDto a = authenticationService.signup(dto);
        assertThat(jwtService.extractUsername(a.token())).isEqualTo("donghyun");

    }

    @Test
    void 로그인() {
        AuthenticationResponseDto dto = new AuthenticationResponseDto(token);
        assertThat(jwtService.extractUsername(token)).isEqualTo("sangyoon");
    }
}