package com.mashup.poten.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findBySnsTypeAndToken(String snsType, String token);
}
