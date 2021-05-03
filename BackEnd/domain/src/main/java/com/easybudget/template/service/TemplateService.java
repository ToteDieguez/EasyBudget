package com.easybudget.template.service;

import com.easybudget.template.Template;
import com.easybudget.user.person.Person;

import java.util.List;
import java.util.Optional;

public interface TemplateService {

    Template create(String templateName, Person person);

    Template addCategoryToTemplate(Long templateID, Long categoryID, Person person);

    Optional<Template> findByIdAndPerson(Long templateID, Person person);

    List<Template> findByPerson(Person person);
}
