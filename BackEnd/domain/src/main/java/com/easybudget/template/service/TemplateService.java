package com.easybudget.template.service;

import com.easybudget.template.Template;
import com.easybudget.user.person.PersonID;

import java.util.List;
import java.util.Optional;

public interface TemplateService {

    Template create(String templateName, PersonID personID);

    Template addCategoryToTemplate(Long templateID, Long categoryID, PersonID personID);

    Optional<Template> findByIdAndPerson(Long templateID, PersonID personID);

    List<Template> findByPersonID(PersonID personID);
}
