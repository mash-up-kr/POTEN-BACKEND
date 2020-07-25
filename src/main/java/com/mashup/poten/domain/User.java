package com.mashup.poten.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User Domain 객체
 * 유저에 대한 비즈니스 로직을 담당
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer userSeq;

    private String snsType;

    private String token;

    @Builder
    public User(Integer userSeq, String snsType, String token) {
        this.userSeq = userSeq;
        this.snsType = snsType;
        this.token = token;
    }

    public void encodingPassword(String token) {
        this.token = token;
    }
}
