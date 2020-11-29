package com.easybudget.user.person.repository;

import com.easybudget.user.person.Person;

public interface PersonRepository {

    Person findByUsername(String username);
}
