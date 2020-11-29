package com.easybudget.repository.user.person;

import com.easybudget.user.person.Person;
import com.easybudget.user.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private PersonJPARepository personJPARepository;

    @Override
    public Person findByUsername(String username) {
        return personJPARepository.findByUsername(username);
    }
}
