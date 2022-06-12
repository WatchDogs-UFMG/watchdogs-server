package br.ufmg.watchdogs.server.api.dto;

import br.ufmg.watchdogs.server.api.model.User;
import br.ufmg.watchdogs.server.api.util.MyDateFormatterUtil;
import br.ufmg.watchdogs.server.api.util.MyDateTimeFormatterUtil;

public class UserDto {

    private Long id;
    private String role;
    private String username;
    private String email;
    private String password;
    private String name;
    private String creationDate;
    private String lastUpdateDate;
    private String birthdayDate;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.role = user.getProfile().getRole().toString();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.creationDate = user.getCreationDate().format(MyDateTimeFormatterUtil.FORMATTER);
        this.lastUpdateDate = user.getLastUpdateDate().format(MyDateTimeFormatterUtil.FORMATTER);
        this.birthdayDate = user.getBirthdayDate().format(MyDateFormatterUtil.FORMATTER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }
}
