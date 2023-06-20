package com.example.skeduler.services;

import com.example.skeduler.dto.AuthenticationRequestDto;
import com.example.skeduler.dto.AuthenticationResponseDto;
import com.example.skeduler.dto.RefreshAccessRequestDto;
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

    private String accesstoken;
    private String refreshtoken;

    @BeforeAll
    public void beforeAll() {
        RegisterRequestDto dto = new RegisterRequestDto("sangyoon", "sy@gmail.com", "sypass", "sangyoon");
        AuthenticationResponseDto a = authenticationService.signup(dto);
        accesstoken = a.accessToken();
        refreshtoken = a.refreshToken();
    }

    @Test
    void 회원가입() {
        RegisterRequestDto dto = new RegisterRequestDto("donghyun", "dh@gmail.com", "dhpass", "dooomhyun");
        AuthenticationResponseDto a = authenticationService.signup(dto);
        assertThat(jwtService.extractUsername(a.accessToken())).isEqualTo("donghyun");

    }

    @Test
    void 로그인() {
        AuthenticationResponseDto dto = new AuthenticationResponseDto(accesstoken, refreshtoken);
        assertThat(jwtService.extractUsername(accesstoken)).isEqualTo("sangyoon");
    }

    @Test
    void refreshAccessToken() {
        RefreshAccessRequestDto dto = new RefreshAccessRequestDto(refreshtoken);
        String newToken = authenticationService.refreshAuth(dto).accessToken();
        assertThat(jwtService.extractUsername(newToken)).isEqualTo("sangyoon");
    }
}