package com.easybudget.category.domain;

import com.easybudget.shared.domain.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category extends EntityBase<Category> {

    @Version
    private Long version;
    @Column(name = "categoryName", nullable = false)
    private String categoryName;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = Objects.requireNonNull(categoryName, "Category Name must not be null");
    }

    public String getCategoryName() {
        return categoryName;
    }
}
