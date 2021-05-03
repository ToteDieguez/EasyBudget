package com.easybudget.template.repository;

import com.easybudget.template.Template;
import com.easybudget.user.person.Person;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository {

    Template save(Template template);

    Optional<Template> findByIdAndPerson(Long templateID, Person person);

    List<Template> findByPerson(Person person);
}
