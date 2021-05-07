package com.easybudget.template.repository;

import com.easybudget.template.Template;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.PersonID;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository {

    Template save(Template template);

    Optional<Template> findByIdAndPersonID(Long templateID, PersonID personID);

    List<Template> findByPersonID(PersonID personID);
}
