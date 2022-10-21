package com.appcenter02.todo04.repository;

import com.appcenter02.todo04.domain.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsrRepository extends JpaRepository<Usr, Long> {
    List<Usr> findAllByOrderByAgeAndJob(int age, String Job);
}