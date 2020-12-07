package com.easybudget.user.person;

import com.easybudget.user.person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("/findPersonByUsername")
    public Person findPersonByUsername() {
        return personService.findByUsername("jose").orElseThrow(() -> new RuntimeException("ERROR"));
    }
}
