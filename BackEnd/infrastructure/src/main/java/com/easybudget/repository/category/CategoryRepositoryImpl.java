package com.easybudget.repository.category;

import com.easybudget.category.Category;
import com.easybudget.category.repository.CategoryRepository;
import com.easybudget.user.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private CategoryJPARepository categoryJPARepository;

    @Override
    @Transactional
    public Category create(Category category) {
        return categoryJPARepository.save(category);
    }

    @Override
    public Optional<Category> findByIdAndPerson(Long categoryID, Person person) {
        return categoryJPARepository.findByIdAndPerson(categoryID, person);
    }
}
