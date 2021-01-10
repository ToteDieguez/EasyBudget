package com.easybudget.category;

import com.easybudget.category.subcategory.SubCategory;
import com.easybudget.category.type.CategoryType;
import com.easybudget.shared.EntityBase;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
public class Category extends EntityBase<Category> {

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CategoryType type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCategory> subCategories;

    public Category() {
        this.subCategories = new ArrayList<>();
    }

    public Category(String name, CategoryType type, List<SubCategory> subCategories) {
        this.name = name;
        this.type = type;
        this.subCategories = subCategories;
    }
}
