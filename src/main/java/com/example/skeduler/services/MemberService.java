package com.example.skeduler.services;

import com.example.skeduler.dto.AuthenticationResponseDto;
import com.example.skeduler.dto.RegisterRequestDto;
import com.example.skeduler.model.Member;
import com.example.skeduler.model.VerificationToken;
import com.example.skeduler.repositories.MemberRepository;
import com.example.skeduler.repositories.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    private String generateVerificationToken(Member member) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setMember(member);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Override
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
            .orElseGet(null);
    }

    private void validateUser(String username) {
        memberRepository.findByUsername(username)
                .ifPresent(e -> {
                    throw new IllegalStateException("이미 등록 된 회원입니다.");
                });
    }

    public long create(Member member) {

        validateUser(member.getUsername());

        memberRepository.save(member);
        return member.getId();
    }
}
