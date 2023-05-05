package com.example.skeduler.services;

import com.example.skeduler.repositories.MemberRepository;
import com.example.skeduler.model.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OAuth2userService {

    private final MemberRepository memberRepository;

    public OAuth2userService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long join(Member member) {

        try {
            validateDuplicateEmail(member);
            memberRepository.save(member);
            return member.getId();
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;

    }

    private void validateDuplicateEmail(Member member) {
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(mem -> {
                    throw new IllegalStateException("exist member");
                });
    }

}
