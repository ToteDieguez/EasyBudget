package com.easybudget.template.repository;

import com.easybudget.template.Template;
import com.easybudget.user.person.Person;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository {

    Template create(Template template);

    Optional<Template> findById(Long templateID);

    List<Template> findTemplatesByPerson(Person person);
}
