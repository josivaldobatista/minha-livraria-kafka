package com.jfb.minhalivraria.repository;

import com.jfb.minhalivraria.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, String> {
}
