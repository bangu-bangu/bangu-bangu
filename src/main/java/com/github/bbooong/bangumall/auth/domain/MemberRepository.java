package com.github.bbooong.bangumall.auth.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface MemberRepository extends Repository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String password);

    Member save(Member member);
}
