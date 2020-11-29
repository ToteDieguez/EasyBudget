package com.easybudget.user.person.service;

import com.easybudget.user.person.Person;

public interface PersonService {

    Person findByUsername(String username);
}
