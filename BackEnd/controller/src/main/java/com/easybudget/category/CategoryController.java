package com.easybudget.category;

import com.easybudget.category.dto.CategoryCreation;
import com.easybudget.category.service.CategoryService;
import com.easybudget.category.type.CategoryType;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.service.security.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public Category create(@CurrentUser Person person, @RequestBody CategoryCreation categoryCreation) {
        Category category = new Category(categoryCreation.getName(), CategoryType.getCategoryType(categoryCreation.getType()), person);
        return categoryService.create(category);
    }
}
