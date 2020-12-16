package com.easybudget.user.person;

import com.easybudget.user.person.service.security.CurrentUser;
import com.easybudget.user.person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("/findPersonByUsername")
    public Person findPersonByUsername(@CurrentUser Person person) {
        return personService.findByUsername(person.getUsername()).orElseThrow(() -> new RuntimeException("ERROR"));
    }
}
