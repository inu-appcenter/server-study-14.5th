package com.appcenter.todolist.repository;

import com.appcenter.todolist.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
