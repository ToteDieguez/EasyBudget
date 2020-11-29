package com.easybudget.repository.user.person;

import com.easybudget.user.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJPARepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
}
