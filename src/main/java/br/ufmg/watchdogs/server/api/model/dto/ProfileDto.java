package br.ufmg.watchdogs.server.api.model.dto;

import br.ufmg.watchdogs.server.api.model.Profile;
import br.ufmg.watchdogs.server.util.DateTimeFormatterUtil;

public class ProfileDto {

    private Long id;
    private String role;
    private String creationDate;
    private String lastUpdateDate;
    private int usersCount;

    public ProfileDto() {
    }

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.role = profile.getRole().toString();
        this.creationDate = profile.getCreationDate().format(DateTimeFormatterUtil.FORMATTER);
        this.lastUpdateDate = profile.getLastUpdateDate().format(DateTimeFormatterUtil.FORMATTER);
        this.usersCount = profile.getUsers().size();
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

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }
}
