package com.easybudget.repository.category;

import com.easybudget.category.Category;
import com.easybudget.category.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private CategoryJPARepository categoryJPARepository;

    @Override
    public Category save(Category category) {
        return categoryJPARepository.save(category);
    }
}
