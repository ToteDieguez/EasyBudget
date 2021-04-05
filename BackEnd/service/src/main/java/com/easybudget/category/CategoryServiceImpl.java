package com.easybudget.category;

import com.easybudget.category.repository.CategoryRepository;
import com.easybudget.category.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public Optional<Category> findById(Long categoryID) {
        return categoryRepository.findById(categoryID);
    }
}
