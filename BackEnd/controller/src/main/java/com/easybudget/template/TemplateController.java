package com.easybudget.template;

import com.easybudget.template.service.TemplateService;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.service.security.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/template")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TemplateController {

    private TemplateService templateService;

    @PostMapping
    public Template create(@RequestParam String name, @CurrentUser Person person) {
        return templateService.create(name, person);
    }

    @GetMapping("/all")
    public List<Template> findAll(@CurrentUser Person person) {
        return templateService.findByPerson(person);
    }

    @PostMapping("/{templateID}/category/{categoryID}")
    public Template addCategoryToTemplate(@PathVariable Long templateID, @PathVariable Long categoryID, @CurrentUser Person person) {
        return templateService.addCategoryToTemplate(templateID, categoryID, person);
    }
}
