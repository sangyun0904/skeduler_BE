package com.example.skeduler.controllers;

import com.example.skeduler.dto.AuthenticationRequestDto;
import com.example.skeduler.dto.RegisterRequestDto;
import com.example.skeduler.model.Member;
import com.example.skeduler.services.JwtService;
import com.example.skeduler.services.MemberService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberControllerTest {

    @Autowired
    MemberController memberController;

    @Autowired
    JwtService jwtService;

    @Autowired
    MemberService memberService;

    @BeforeAll
    void beforeAll() {

    }

    @Test
    void register() {
        RegisterRequestDto requestDto = new RegisterRequestDto("sang", "skgj@gmail.com", "password", "sang");

        String token = memberController.register(requestDto).getBody().token();
        Member member = memberService.loadUserByUsername("sang");

        assertThat(jwtService.isTokenValid(token, member)).isEqualTo(true);
    }

    @Test
    void authenticate() {
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto("sang", "password");
        String token = memberController.authenticate(requestDto).getBody().token();

        Member member = memberService.loadUserByUsername("sang");

        assertThat(jwtService.isTokenValid(token, member)).isEqualTo(true);
    }
}