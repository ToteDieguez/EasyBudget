package com.easybudget.template;

import com.easybudget.template.service.TemplateService;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.service.security.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TemplateController {

    private TemplateService templateService;

    @PostMapping("/create")
    public Template createTemplate(@CurrentUser Person person, @RequestParam String name) {
        Template template = new Template(name, person);
        return templateService.create(template);
    }

    @GetMapping("/get/all")
    public Template getAllTemplates (@CurrentUser Person person, @RequestParam String name) {
        Template template = new Template(name, person);
        return templateService.create(template);
    }

    @PostMapping("/add/category")
    public Template addCategory(@CurrentUser Person person, @RequestParam String name) {
        Template template = new Template(name, person);
        return templateService.addCategoryToTemplate(null, null);
    }
}
