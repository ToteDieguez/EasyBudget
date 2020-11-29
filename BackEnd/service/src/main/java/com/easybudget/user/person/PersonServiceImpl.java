package com.easybudget.user.person;

import com.easybudget.user.person.repository.PersonRepository;
import com.easybudget.user.person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

}
