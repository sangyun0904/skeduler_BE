package com.example.skeduler.services;

import com.example.skeduler.dto.*;
import com.example.skeduler.model.Member;
import com.example.skeduler.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        String accessToken = jwtService.generateAccessToken(member);
        String refreshToken = jwtService.generateRefreshToken(member);
        return new AuthenticationResponseDto(accessToken, refreshToken);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        Member member = memberRepository.findByUsername(request.username())
                .orElseThrow();
        String accessToken = jwtService.generateAccessToken(member);
        String refreshToken = jwtService.generateRefreshToken(member);
        return new AuthenticationResponseDto(accessToken, refreshToken);
    }

    public RefreshAccessResponseDto refreshAuth(RefreshAccessRequestDto request) {
        String token = request.refreshToken();
        String username = jwtService.extractUsername(token);

        Member member = memberRepository.findByUsername(username)
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(member);

        return new RefreshAccessResponseDto(accessToken);
    }
}
