package com.easybudget.category.repository;

import com.easybudget.category.Category;

import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    Optional<Category> findById(Long categoryID);
}
