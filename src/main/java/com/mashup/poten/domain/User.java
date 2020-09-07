package com.mashup.poten.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * User Domain 객체
 * 유저에 대한 비즈니스 로직을 담당
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer userSeq;

    private String snsType;

    @Column(length = 400)
    private String token;

    @Column(unique = true)
    private String nickname;

    @Builder
    public User(Integer userSeq, String snsType, String token, String nickname) {
        this.userSeq = userSeq;
        this.snsType = snsType;
        this.token = token;
        this.nickname = nickname;
    }

    public void encodingPassword(String token) {
        this.token = token;
    }

}
