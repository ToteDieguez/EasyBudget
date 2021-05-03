package com.easybudget.template;

import com.easybudget.category.Category;
import com.easybudget.category.service.CategoryService;
import com.easybudget.template.repository.TemplateRepository;
import com.easybudget.template.service.TemplateService;
import com.easybudget.user.person.Person;
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
    public Template create(String templateName, Person person) {
        Template template = new Template(templateName, person);
        return templateRepository.save(template);
    }

    @Override
    @Transactional
    public Template addCategoryToTemplate(Long templateID, Long categoryID, Person person) {
        Template template = this.findByIdAndPerson(templateID, person).orElseThrow(NoSuchElementException::new);
        Category category = categoryService.findByIdAndPerson(categoryID, person).orElseThrow(NoSuchElementException::new);
        boolean isCategoryInTemplate = template.getCategories()
                .stream()
                .filter(categoryInTemplate -> categoryInTemplate.equals(category))
                .findFirst()
                .isPresent();
        if (Boolean.TRUE.equals(isCategoryInTemplate)) {
            return template;
        } else {
            template.addCategory(category);
            return templateRepository.save(template);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Template> findByIdAndPerson(Long templateID, Person person) {
        return templateRepository.findByIdAndPerson(templateID, person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Template> findByPerson(Person person) {
        return templateRepository.findByPerson(person);
    }

}
