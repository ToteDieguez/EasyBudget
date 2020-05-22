package com.easybudget.category.repository.imports;

import com.easybudget.category.domain.Category;
import com.easybudget.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryJPARepository implements CategoryRepository {

    private SpringJPACategoryRepository springJPACategoryRepository;

    @Autowired
    public CategoryJPARepository(SpringJPACategoryRepository springJPACategoryRepository) {
        this.springJPACategoryRepository = springJPACategoryRepository;
    }

    @Override
    public Category save(Category category) {
        return springJPACategoryRepository.save(category);
    }
}
