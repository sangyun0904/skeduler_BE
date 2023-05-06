package com.example.skeduler.services;

import com.example.skeduler.repositories.MemberRepository;
import com.example.skeduler.model.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OAuth2userService {

    private final MemberRepository memberRepository;

    public OAuth2userService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long join(Member member) {

        Optional<Member> member1 = memberRepository.findByEmail(member.getEmail());

        if (member1.isPresent()) {
            return member1.get().getId();
        }
        else {
            memberRepository.save(member);
            return member.getId();
        }

    }


}
