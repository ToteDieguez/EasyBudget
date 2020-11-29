package com.easybudget.person;

import com.easybudget.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private PersonJPARepository personJPARepository;

    @Override
    public List<Person> findAll() {
        return personJPARepository.findAll();
    }
}
