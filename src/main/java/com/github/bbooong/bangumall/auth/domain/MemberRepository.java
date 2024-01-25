package com.github.bbooong.bangumall.auth.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String password);

    Member save(Member member);
}
