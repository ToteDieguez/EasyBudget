package com.easybudget.category.repository;

import com.easybudget.category.Category;
import com.easybudget.user.person.PersonID;

import java.util.Optional;

public interface CategoryRepository {

    Category create(Category category);

    Optional<Category> findByIdAndPersonID(Long categoryID, PersonID personID);
}
