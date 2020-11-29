package com.easybudget.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJPARepository extends JpaRepository<Person, Long> {
}
