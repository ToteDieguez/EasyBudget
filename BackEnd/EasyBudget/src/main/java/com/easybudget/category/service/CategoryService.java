package com.easybudget.category.service;

import com.easybudget.category.domain.Category;
import com.easybudget.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory(String categoryName){
        categoryRepository.save(new Category(categoryName));
    }
}
