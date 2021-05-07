package com.easybudget.user.person;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@ToString
public final class PersonID implements Serializable {

    @Column(name = "person_id")
    private Long personID;

    protected PersonID() {
        super();
    }

    public PersonID(Long personID) {
        this();
        this.setPersonID(personID);
    }

    private void setPersonID(Long personID){
        if (personID == null){
            throw new IllegalArgumentException("Person ID cannot be null");
        }
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonID that = (PersonID) o;
        return personID.equals(that.personID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personID);
    }
}
