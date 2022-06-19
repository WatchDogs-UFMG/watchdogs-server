package br.ufmg.watchdogs.server.api.model.form;

import br.ufmg.watchdogs.server.api.exception.MyInvalidEnumValueException;
import br.ufmg.watchdogs.server.api.model.Profile;
import br.ufmg.watchdogs.server.api.model.UserProfiles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UpdateProfileForm {

    @NotNull(message = "Esse campo deve ser preenchido")
    @Positive(message = "O id deve ser um número inteiro e positivo")
    private Long id;

    @NotBlank(message = "Esse campo deve ser preenchido")
    private String role;

    public UpdateProfileForm() {
    }

    public UpdateProfileForm(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Profile updateProfile(Profile profile, String role) {

        try {

            return profile.setRole(UserProfiles.valueOf(role));

        } catch (IllegalArgumentException exception) {

            throw new MyInvalidEnumValueException(
                    "Não foi possível encontrar definição do enum UserProfiles." + role,
                    this.getClass().getName()
            );
        }
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
}
