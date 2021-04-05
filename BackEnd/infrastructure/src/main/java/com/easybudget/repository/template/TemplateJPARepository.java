package com.easybudget.repository.template;

import com.easybudget.template.Template;
import com.easybudget.user.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateJPARepository extends JpaRepository<Template, Long> {

    List<Template> findTemplatesByPerson(Person person);
}
