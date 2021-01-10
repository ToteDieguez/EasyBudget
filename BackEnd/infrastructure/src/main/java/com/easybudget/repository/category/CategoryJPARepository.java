package com.easybudget.repository.category;

import com.easybudget.category.Category;
import com.easybudget.user.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJPARepository extends JpaRepository<Category, Long> {
}
