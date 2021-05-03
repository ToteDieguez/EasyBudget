package com.easybudget.category;

import com.easybudget.category.repository.CategoryRepository;
import com.easybudget.category.service.CategoryService;
import com.easybudget.user.person.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category create(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findByIdAndPerson(Long categoryID, Person person) {
        return categoryRepository.findByIdAndPerson(categoryID, person);
    }
}
