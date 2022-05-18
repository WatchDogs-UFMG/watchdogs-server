package br.ufmg.watchdogs.server.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Profile profile;

    private String username;

    private String email;

    private String password;

    private String name;

    private LocalDate birthdayDate;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdateDate;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public User setProfile(Profile profile) {
        this.profile = profile;
        profile.addUser(this);
        return this;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<Profile> getAuthorities() {
        return List.of(this.profile);
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public User setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public User setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public User setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }


}
