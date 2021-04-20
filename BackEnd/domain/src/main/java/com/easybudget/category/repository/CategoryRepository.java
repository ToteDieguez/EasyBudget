package com.easybudget.category.repository;

import com.easybudget.category.Category;
import com.easybudget.user.person.Person;

import java.util.Optional;

public interface CategoryRepository {

    Category create(Category category);

    Optional<Category> findByIdAndPerson(Long categoryID, Person person);
}
