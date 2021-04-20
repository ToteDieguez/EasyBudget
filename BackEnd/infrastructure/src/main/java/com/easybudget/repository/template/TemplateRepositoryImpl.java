package com.easybudget.repository.template;

import com.easybudget.template.Template;
import com.easybudget.template.repository.TemplateRepository;
import com.easybudget.user.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TemplateRepositoryImpl implements TemplateRepository {

    private TemplateJPARepository templateJPARepository;

    @Override
    public Template create(Template template) {
        return templateJPARepository.save(template);
    }

    @Override
    public Optional<Template> findByIdAndPerson(Long templateID, Person person) {
        return templateJPARepository.findByIdAndPerson(templateID, person);
    }

    @Override
    public List<Template> findByPerson(Person person) {
        return templateJPARepository.findByPerson(person);
    }
}
