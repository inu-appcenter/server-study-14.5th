package com.appcenter.todolistapi.repository;

import com.appcenter.todolistapi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String Email);
    Optional<Member> findByMemberId(UUID memberId);

    @Transactional
    Optional<Member> deleteMemberByMemberId(UUID memberId);

}
