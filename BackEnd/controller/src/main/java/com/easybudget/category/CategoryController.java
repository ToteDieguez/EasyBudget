package com.easybudget.category;

import com.easybudget.category.dto.CategoryDTO;
import com.easybudget.category.service.CategoryService;
import com.easybudget.category.type.CategoryType;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.service.security.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public Category create(@CurrentUser Person person, @RequestBody CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getName(), CategoryType.getCategoryType(categoryDTO.getType()), person.getPersonID());
        return categoryService.create(category);
    }

    @GetMapping("/{id}")
    public Optional<Category> findCategory(@PathVariable Long id, @CurrentUser Person person) {
        return categoryService.findByIdAndPersonID(id, person.getPersonID());
    }
}
