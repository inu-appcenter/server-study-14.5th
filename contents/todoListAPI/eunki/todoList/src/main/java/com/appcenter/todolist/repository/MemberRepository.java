package com.appcenter.todolist.repository;

import com.appcenter.todolist.domain.Member;
import com.appcenter.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByOrderByModifiedAtDesc();

}
