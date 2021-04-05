package com.easybudget.category.service;

import com.easybudget.category.Category;

import java.util.Optional;

public interface CategoryService {

    Category create(Category category);

    Optional<Category> findById(Long categoryID);
}
