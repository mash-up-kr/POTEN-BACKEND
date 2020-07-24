package com.mashup.poten.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Assignment Domain 객체
 * 과제에 대한 비즈니스 로직을 담당
 */
@Getter
@Entity
public class Assignment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer assignmentSeq;
}
