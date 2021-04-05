package com.easybudget.template;

import com.easybudget.category.Category;
import com.easybudget.category.service.CategoryService;
import com.easybudget.template.repository.TemplateRepository;
import com.easybudget.template.service.TemplateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private TemplateRepository templateRepository;

    private CategoryService categoryService;

    @Override
    public Template create(Template template) {
        return templateRepository.create(template);
    }

    @Override
    public Template addCategoryToTemplate(Long templateID, Long categoryID) {
        Template template = findById(templateID).orElseThrow(NoSuchElementException::new);
        Category category = categoryService.findById(categoryID).orElseThrow(NoSuchElementException::new);
        boolean isCategoryInTemplate = template.getCategories().stream()
                .filter(categoryInTemplate -> categoryInTemplate.equals(category))
                .findFirst()
                .isPresent();
        if (isCategoryInTemplate) {
            return template;
        } else {
            return templateRepository.create(template);
        }
    }

    @Override
    public Optional<Template> findById(Long templateID) {
        return templateRepository.findById(templateID);
    }

    @Override
    public List<Template> findAll(Long id) {
        return null;
    }
}
