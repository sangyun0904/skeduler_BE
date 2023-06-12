package com.example.skeduler.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.as;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void testEquals() {
        Member member1 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "sangyoon");
        Member member2 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "sangyoon");
        Member member3 = new Member((long) 2, "sangyoon", "password", "sang@sang.com", "sangyoon");
        Member member4 = new Member((long) 1, "yoonjung", "password", "sang@sang.com", "sangyoon");
        Member member5 = new Member((long) 1, "sangyoon", "password2", "sang@sang.com", "sangyoon");
        Member member6 = new Member((long) 1, "sangyoon", "password", "yoon@sang.com", "sangyoon");
        Member member7 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "yoonjung");
        Member member8 = null;
        Task task1 = new Task();

        assertThat(member1.equals(member1)).isEqualTo(true);
        assertThat(member1.equals(member2)).isEqualTo(true);
        assertThat(member1.equals(member3)).isEqualTo(false);
        assertThat(member1.equals(member4)).isEqualTo(false);
        assertThat(member1.equals(member5)).isEqualTo(false);
        assertThat(member1.equals(member6)).isEqualTo(false);
        assertThat(member1.equals(member7)).isEqualTo(false);
        assertThat(member1.equals(member8)).isEqualTo(false);
        assertThat(member1.equals(task1)).isEqualTo(false);
    }

    @Test
    void testHashCode() {
        Member member1 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "sangyoon");
        System.out.println(member1.hashCode());
        assertThat(member1.hashCode()).isEqualTo(22281640);
    }

    @Test
    void builder() {
        Member member = Member
                .builder()
                .id((long) 1)
                .username("sangyoon")
                .password("password")
                .email("sang@sang.com")
                .githubId("sangyoon")
                .build();

        assertThat(Member
                .builder()
                .id((long) 1)
                .username("sangyoon")
                .password("password")
                .email("sang@sang.com")
                .githubId("sangyoon")
                .toString()).isEqualTo("Member.MemberBuilder(id=1, username=sangyoon, password=password, email$value=sang@sang.com, githubId$value=sangyoon)");

        assertThat(member.getId()).isEqualTo((long) 1);
        String result = "Member{" +
                "id=" + 1 +
                ", username='" + "sangyoon" + '\'' +
                ", password='" + "password" + '\'' +
                ", email='" + "sang@sang.com" + '\'' +
                ", githubId='" + "sangyoon" + '\'' +
                '}';
        assertThat(member.toString()).isEqualTo(result);
    }

    @Test
    void builderException() {
        assertThrows(NullPointerException.class, () -> Member
                .builder()
                .id((long) 1)
                .username(null)
                .password("password")
                .email("sang@sang.com")
                .githubId("sangyoon")
                .build());
        assertThrows(NullPointerException.class, () -> Member
                .builder()
                .id((long) 1)
                .username("sangyoon")
                .password(null)
                .email("sang@sang.com")
                .githubId("sangyoon")
                .build());
    }

    @Test
    void constructorException() {
        assertThrows(NullPointerException.class, () -> new Member((long) 1, null, "password", "sang@sang.com", "sangyoon"));
        assertThrows(NullPointerException.class, () -> new Member((long) 1, "sangyoon", null, "sang@sang.com", "sangyoon"));
    }

    @Test
    void getAuthorities() {
        Member member1 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "sangyoon");
        assertThat(member1.getAuthorities()).isEqualTo(null);
    }

    @Test
    void isAccountNonExpired() {
        Member member1 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "sangyoon");
        assertThat(member1.isAccountNonExpired()).isEqualTo(true);
    }

    @Test
    void isAccountNonLocked() {
        Member member1 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "sangyoon");
        System.out.println(member1.isAccountNonLocked());
        assertThat(member1.isAccountNonLocked()).isEqualTo(true);
    }

    @Test
    void isCredentialsNonExpired() {
        Member member1 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "sangyoon");
        assertThat(member1.isCredentialsNonExpired()).isEqualTo(true);
    }

    @Test
    void isEnabled() {
        Member member1 = new Member((long) 1, "sangyoon", "password", "sang@sang.com", "sangyoon");
        assertThat(member1.isEnabled()).isEqualTo(true);
    }

}