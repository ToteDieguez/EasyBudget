package com.easybudget.person.repository;

import com.easybudget.person.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findAll();
}
