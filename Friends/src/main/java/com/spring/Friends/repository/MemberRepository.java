package com.spring.Friends.repository;

import com.spring.Friends.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
public interface MemberRepository extends JpaRepository<Member,Long> { // <테이블 명, 자료형>

    // 지원 메서드 - save(), findAll(), findById(), deleteById()

    // 이메일로
    public Optional<Member> findByEmail(String email);
}
