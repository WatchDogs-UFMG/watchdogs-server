package br.ufmg.watchdogs.server.api.model.form;

import br.ufmg.watchdogs.server.api.model.Profile;
import br.ufmg.watchdogs.server.api.model.UserProfiles;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CreateProfileForm {

    @NotBlank(message = "Esse campo deve ser preenchido")
    private String role;

    public CreateProfileForm() {
    }

    public Profile convertToProfile(UserProfiles role) {
        return new Profile()
                .setRole(role)
                .setCreationDate(LocalDateTime.now())
                .setLastUpdateDate(LocalDateTime.now());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
