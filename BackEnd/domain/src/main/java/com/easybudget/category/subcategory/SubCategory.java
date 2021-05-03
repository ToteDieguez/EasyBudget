package com.easybudget.category.subcategory;

import com.easybudget.category.Category;
import com.easybudget.shared.EntityBase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sub_category")
public class SubCategory extends EntityBase<SubCategory> {

    @Column(name = "name", nullable = false)
    @Getter
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_subcategory_category"))
    @Getter
    @JsonBackReference
    private Category category;

    public SubCategory() {
    }

    public SubCategory(String name, Category category) {
        this();
        this.name = name;
        this.category = category;
    }
}
