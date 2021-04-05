package com.easybudget.template;

import com.easybudget.category.Category;
import com.easybudget.shared.EntityBase;
import com.easybudget.user.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "template")
@Getter
public class Template extends EntityBase<Template> {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "template_category",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            foreignKey = @ForeignKey(name = "Fk_template__template_category"))
    private Set<Category> categories;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_template_person"))
    @JsonIgnore
    private Person person;

    public Template() {
        this.categories = new HashSet<>();
    }

    public Template(String name, Person person) {
        this();
        this.name = name;
        this.person = person;
    }
}
