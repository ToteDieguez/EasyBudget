package com.easybudget.user.person.service;

import com.easybudget.user.person.Person;

import java.util.Optional;

public interface PersonService {

    Optional<Person> findByUsername(String username);
}
