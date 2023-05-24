package com.example.skeduler.services;

import com.example.skeduler.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 회원_생성() {
        Member member = Member.builder()
                .username("Jinx")
                .password("jinxpass")
                .email("jinx@jinx.com")
                .build();

        long userId = memberService.create(member);

        assertThat(memberService.loadUserByUsername("Jinx").getId()).isEqualTo(userId);
    }

    @Test
    void username_중복() {
        Member member1 = Member.builder()
                .username("Rox")
                .password("Roxpass")
                .email("rox@rox.com")
                .build();

        Member member2 = Member.builder()
                .username("Rox")
                .password("Roxpass")
                .email("rox@rox.com")
                .build();

        memberService.create(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.create(member2));
        assertThat(e.getMessage()).isEqualTo("이미 등록 된 회원입니다.");
    }

}