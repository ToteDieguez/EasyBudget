package com.easybudget.user.auth;

import com.easybudget.user.auth.service.AuthPersonServiceDetail;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.PersonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthPersonServiceDetailImpl implements AuthPersonServiceDetail {

    private PersonServiceImpl personServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> findFirst = personServiceImpl.findByUsername(username);

        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();
    }
}
