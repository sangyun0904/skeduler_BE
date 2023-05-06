package com.example.skeduler.controllers;

import com.example.skeduler.model.Member;
import com.example.skeduler.services.OAuth2userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    private final OAuth2userService oAuth2userService;

    @Autowired
    public HomeController(OAuth2userService oAuth2userService) {
        this.oAuth2userService = oAuth2userService;
    }

    @GetMapping("/user")
    public Map<String, String> user(@AuthenticationPrincipal OAuth2User principal) {
        Member member = new Member();
        member.setUsername(principal.getAttribute("name"));
        member.setEmail(principal.getAttribute("email"));

        long userId = oAuth2userService.join(member);

        Map<String, String> m1 = new HashMap<String, String>();
        m1.put("name", principal.getAttribute("name"));
        m1.put("id", userId+"");

        return m1;
    }

}
