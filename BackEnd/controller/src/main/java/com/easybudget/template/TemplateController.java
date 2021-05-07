package com.easybudget.template;

import com.easybudget.template.dto.TemplateDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/template")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TemplateController {

    private TemplateService templateService;

    @PostMapping
    public TemplateDTO create(@RequestParam String name, @CurrentUser Person person) {
        Template template = templateService.create(name, person.getPersonID());
        return this.templateDTOMapper(template);
    }

    @GetMapping("/all")
    public List<TemplateDTO> findAll(@CurrentUser Person person) {
        return templateService.findByPersonID(person.getPersonID())
                .stream()
                .map(template -> this.templateDTOMapper(template))
                .collect((Collectors.toList()));
    }

    @PostMapping("/{templateID}/category/{categoryID}")
    public Template addCategoryToTemplate(@PathVariable Long templateID, @PathVariable Long categoryID, @CurrentUser Person person) {
        return templateService.addCategoryToTemplate(templateID, categoryID, person.getPersonID());
    }

    private TemplateDTO templateDTOMapper(Template template){
        TemplateDTO templateDTO = new TemplateDTO();
        templateDTO.setTemplateID(template.getId());
        templateDTO.setName(template.getName());
        return templateDTO;
    }
}
