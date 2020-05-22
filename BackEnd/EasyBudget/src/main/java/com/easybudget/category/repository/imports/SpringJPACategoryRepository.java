package com.easybudget.category.repository.imports;

import com.easybudget.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJPACategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);
}
