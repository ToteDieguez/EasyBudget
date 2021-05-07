package com.easybudget.template;

import com.easybudget.category.Category;
import com.easybudget.category.service.CategoryService;
import com.easybudget.template.repository.TemplateRepository;
import com.easybudget.template.service.TemplateService;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.PersonID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private TemplateRepository templateRepository;

    private CategoryService categoryService;

    @Override
    @Transactional
    public Template create(String templateName, PersonID personID) {
        Template template = new Template(templateName, personID);
        return templateRepository.save(template);
    }

    @Override
    @Transactional
    public Template addCategoryToTemplate(Long templateID, Long categoryID, PersonID personID) {
        Template template = this.findByIdAndPerson(templateID, personID).orElseThrow(NoSuchElementException::new);
        Category category = categoryService.findByIdAndPersonID(categoryID, personID).orElseThrow(NoSuchElementException::new);
        template.addCategory(category);
        return templateRepository.save(template);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Template> findByIdAndPerson(Long templateID, PersonID personID) {
        return templateRepository.findByIdAndPersonID(templateID, personID);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Template> findByPersonID(PersonID personID) {
        return templateRepository.findByPersonID(personID);
    }

}
