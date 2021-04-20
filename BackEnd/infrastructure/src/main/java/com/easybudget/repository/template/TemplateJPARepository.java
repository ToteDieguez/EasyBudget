package com.easybudget.repository.template;

import com.easybudget.template.Template;
import com.easybudget.user.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TemplateJPARepository extends JpaRepository<Template, Long> {

    Optional<Template> findByIdAndPerson(Long templateID, Person person);

    List<Template> findByPerson(Person person);
}
