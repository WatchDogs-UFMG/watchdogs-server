package br.ufmg.watchdogs.server.api.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserProfiles role = UserProfiles.ROLE_USER;

    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public Profile() {
    }

    public Profile(
            Long id,
            List<User> users,
            UserProfiles role,
            LocalDateTime creationDate,
            LocalDateTime lastUpdateDate
    ) {
        this.id = id;
        this.users = users;
        this.role = role;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String getAuthority() {
        return this.role.toString();
    }

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public UserProfiles getRole() {
        return role;
    }

    public Profile setRole(UserProfiles role) {
        this.role = role;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Profile setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Profile addUser(User user) {
        this.users.add(user);
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Profile setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Profile setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }
}
