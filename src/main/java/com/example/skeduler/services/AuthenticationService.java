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
                .username(registerRequestDto.getUsername())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .githubId(registerRequestDto.getGithubId())
                .build();
        memberRepository.save(member);
        var jwtToken = jwtService.generateAccessToken(member);
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateAccessToken(member);
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }
}
