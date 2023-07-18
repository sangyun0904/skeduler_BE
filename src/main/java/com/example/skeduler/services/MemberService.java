package com.example.skeduler.services;

import com.example.skeduler.model.Member;
import com.example.skeduler.model.News;
import com.example.skeduler.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {

        return memberRepository.findByUsername(username)
            .orElseGet(null);



    }
}
