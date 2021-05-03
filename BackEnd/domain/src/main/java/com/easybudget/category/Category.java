package com.easybudget.category;

import com.easybudget.category.subcategory.SubCategory;
import com.easybudget.category.type.CategoryType;
import com.easybudget.shared.EntityBase;
import com.easybudget.template.Template;
import com.easybudget.user.person.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends EntityBase<Category> {

    @Column(name = "name", nullable = false)
    @Getter
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @Getter
    private CategoryType type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
    @Getter
    @JsonManagedReference
    private List<SubCategory> subCategories;

    @ManyToMany
    @JoinTable(
            name = "template_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "template_id"),
            foreignKey = @ForeignKey(name = "Fk_category__template_category"))
    @Getter
    private Set<Template> templates;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_category_person"))
    @JsonIgnore
    private Person person;

    public Category() {
        this.subCategories = new ArrayList<>();
    }

    public Category(String name, CategoryType type, Person person) {
        this();
        this.name = name;
        this.type = type;
        this.person = person;
    }

}
