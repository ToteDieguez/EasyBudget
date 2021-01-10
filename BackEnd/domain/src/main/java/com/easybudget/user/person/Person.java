package com.easybudget.user.person;

import com.easybudget.shared.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "person")
@Getter
@JsonIgnoreProperties(
        value = {"password", "accountNonExpired", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"},
        allowGetters = true
)
public class Person extends EntityBase<Person> implements UserDetails {

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
}
