package com.mashup.poten.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User Domain 객체
 * 유저에 대한 비즈니스 로직을 담당
 */

@Getter
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer userSeq;

    private String userId;

    private String userPassword;

    @Builder
    public User(Integer userSeq, String userId, String userPassword) {
        this.userSeq = userSeq;
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
