package com.easybudget.template;

import com.easybudget.category.Category;
import com.easybudget.shared.EntityBase;
import com.easybudget.user.person.PersonID;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "template")
public class Template extends EntityBase<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    @Getter
    private String name;

    @ManyToMany
    @JoinTable(
            name = "template_category",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            foreignKey = @ForeignKey(name = "Fk_template__template_category"))
    @Getter
    private List<Category> categories;

    @Embedded
    @JoinColumn(name = "person_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_template_person"))
    private PersonID personID;

    public Template() {
        this.categories = new ArrayList<>();
    }

    public Template(String name, PersonID personID) {
        this();
        this.name = name;
        this.personID = personID;
    }

    public void addCategory(Category category) {
        boolean isCategoryInTemplate = this.getCategories()
                .stream()
                .filter(categoryInTemplate -> categoryInTemplate.equals(category))
                .findFirst().isPresent();
        if (!isCategoryInTemplate) {
            this.categories.add(category);
        }
    }

    @Override
    public Long id() {
        return this.id;
    }
}
