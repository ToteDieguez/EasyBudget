package com.easybudget.category.service;

import com.easybudget.category.Category;
import com.easybudget.user.person.Person;

import java.util.Optional;

public interface CategoryService {

    Category create(Category category);

    Optional<Category> findByIdAndPerson(Long categoryID, Person person);
}
