package com.easybudget.user.person;

import com.easybudget.shared.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "person")
@Getter
@JsonIgnoreProperties(
        value = {"password", "accountNonExpired", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"},
        allowGetters = true
)
public class Person extends EntityBase<PersonID> implements UserDetails {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    @Transient
    private String password;

    @Column(name = "accountNonExpired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public PersonID id(){
        return new PersonID(this.id);
    }

}
