package com.easybudget.repository.template;

import com.easybudget.template.Template;
import com.easybudget.template.repository.TemplateRepository;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.PersonID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TemplateRepositoryImpl implements TemplateRepository {

    private TemplateJPARepository templateJPARepository;

    @Override
    public Template save(Template template) {
        return templateJPARepository.save(template);
    }

    @Override
    public Optional<Template> findByIdAndPersonID(Long templateID, PersonID personID) {
        return templateJPARepository.findByIdAndPersonID(templateID, personID);
    }

    @Override
    public List<Template> findByPersonID(PersonID personID) {
        return templateJPARepository.findByPersonID(personID);
    }
}
