package com.easybudget.repository.category;

import com.easybudget.category.Category;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.PersonID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryJPARepository extends JpaRepository<Category, Long> {

    Optional<Category> findByIdAndPersonID(Long categoryID, PersonID personID);
}
