package com.example.skeduler.services;

import com.example.skeduler.dto.RegisterRequest;
import com.example.skeduler.model.Member;
import com.example.skeduler.model.VerificationToken;
import com.example.skeduler.repositories.MemberRepository;
import com.example.skeduler.repositories.VerificationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public MemberService(MemberRepository memberRepository, VerificationTokenRepository verificationTokenRepository) {
        this.memberRepository = memberRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }


    @Transactional
    public void signup(RegisterRequest registerRequest) {
        Member member = new Member();
        member.setUsername(registerRequest.getUsername());
        member.setEmail(registerRequest.getEmail());
        member.setPassword(registerRequest.getPassword());
        member.setGithubId(registerRequest.getGithubId());
        memberRepository.save(member);
    }

    private String generateVerificationToken(Member member) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setMember(member);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
