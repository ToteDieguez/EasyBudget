package com.easybudget.category;

import com.easybudget.category.dto.CategoryDTO;
import com.easybudget.category.service.CategoryService;
import com.easybudget.category.subcategory.SubCategory;
import com.easybudget.category.type.CategoryType;
import com.easybudget.template.Template;
import com.easybudget.template.dto.TemplateDTO;
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

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public CategoryDTO create(@CurrentUser Person person, @RequestBody CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getName(), CategoryType.getCategoryType(categoryDTO.getType()), person.id());
        return this.categoryDTOMapper(categoryService.create(category));
    }

    @GetMapping("/{id}")
    public CategoryDTO findCategory(@PathVariable Long id, @CurrentUser Person person) {
        return this.categoryDTOMapper(categoryService.findByIdAndPersonID(id, person.id()).orElseThrow(() -> new NoSuchElementException("Category doesn't exist")));
    }

    private CategoryDTO categoryDTOMapper(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setType(category.getType().name());
        categoryDTO.setName(category.getName());
        categoryDTO.setId(category.id());
        categoryDTO.setSubCategories(category.getSubCategories().stream().collect(Collectors.toMap(SubCategory::id, SubCategory::getName)));
        return categoryDTO;
    }
}
