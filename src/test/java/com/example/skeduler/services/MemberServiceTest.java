package com.example.skeduler.services;

import com.example.skeduler.dto.AuthenticationRequestDto;
import com.example.skeduler.dto.RegisterRequestDto;
import com.example.skeduler.model.Member;
import com.example.skeduler.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private AuthenticationService authenticationService;

    @BeforeEach
    public void BeforeAll() {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("sangyoon", "sang@naver.com", "password", "sang");

        authenticationService.signup(registerRequestDto);

    }

    @Test
    void 맴버조회() {
        assertThat(memberService.loadUserByUsername("sangyoon").getUsername()).isEqualTo("sangyoon");
    }

}