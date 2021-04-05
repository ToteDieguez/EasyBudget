package com.easybudget.template.service;

import com.easybudget.template.Template;

import java.util.List;
import java.util.Optional;

public interface TemplateService {

    Template create(Template template);

    Template addCategoryToTemplate(Long templateID, Long categoryID);

    Optional<Template> findById(Long id);

    List<Template> findAll(Long id);
}
