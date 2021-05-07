package com.easybudget.category.service;

import com.easybudget.category.Category;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.PersonID;

import java.util.Optional;

public interface CategoryService {

    Category create(Category category);

    Optional<Category> findByIdAndPersonID(Long categoryID, PersonID personID);
}
