package com.example.skeduler.services;

import com.example.skeduler.dto.AuthenticationRequestDto;
import com.example.skeduler.dto.AuthenticationResponseDto;
import com.example.skeduler.dto.RegisterRequestDto;
import com.example.skeduler.model.Member;
import com.example.skeduler.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponseDto signup(RegisterRequestDto registerRequestDto) {
        Member member = Member
                .builder()
                .username(registerRequestDto.username())
                .email(registerRequestDto.email())
                .password(passwordEncoder.encode(registerRequestDto.password()))
                .githubId(registerRequestDto.githubId())
                .build();
        memberRepository.save(member);
        var jwtToken = jwtService.generateAccessToken(member);
        return new AuthenticationResponseDto(jwtToken);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var member = memberRepository.findByUsername(request.username())
                .orElseThrow();
        var jwtToken = jwtService.generateAccessToken(member);
        return new AuthenticationResponseDto(jwtToken);
    }
}
