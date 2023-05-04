package com.example.skeduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HomeController {

    private final OAuth2userService oAuth2userService;

    @Autowired
    public HomeController(OAuth2userService oAuth2userService) {
        this.oAuth2userService = oAuth2userService;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        Member member = new Member();
        member.setUsername(principal.getName());
        member.setEmail(principal.getAttribute("email"));

        oAuth2userService.join(member);

        return Collections.singletonMap("user : ", principal);
    }

}
