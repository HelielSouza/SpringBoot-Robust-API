package com.robust.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robust.api.data.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
