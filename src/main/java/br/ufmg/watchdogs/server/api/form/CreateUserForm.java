package br.ufmg.watchdogs.server.api.form;

import br.ufmg.watchdogs.server.api.exception.MyDataNotFoundException;
import br.ufmg.watchdogs.server.api.model.Profile;
import br.ufmg.watchdogs.server.api.model.User;
import br.ufmg.watchdogs.server.util.DateFormatterUtil;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class CreateUserForm {

    @NotBlank(message = "Esse campo deve ser preenchido")
    private String role;

    @NotBlank(message = "Esse campo deve ser preenchido")
    private String username;

    @NotBlank(message = "Esse campo deve ser preenchido")
    @Email(message = "Forneça um email válido")
    private String email;

    @NotBlank(message = "Esse campo deve ser preenchido")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$", message = "A senha deve conter apenas letras e números")
    @Size(min = 8, max = 16, message = "A senha deve conter entre 8 e 16 caracteres")
    private String password;

    @NotBlank(message = "Esse campo deve ser preenchido")
    private String name;

    @Pattern(
            regexp = "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$",
            message = "Forneça uma data de nascimento válida!"
    )
    private String birthdayDate;

    public CreateUserForm(String username, String email, String password, String name, String birthdayDate, String profile) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.role = profile;
    }

    public CreateUserForm() {
    }

    public User convertToUser(Profile profile, String encryptedPassword) {

        try {

            return new User()
                    .setProfile(profile)
                    .setUsername(this.username)
                    .setEmail(this.email)
                    .setPassword(encryptedPassword)
                    .setName(this.name)
                    .setCreationDate(LocalDateTime.now())
                    .setLastUpdateDate(LocalDateTime.now())
                    .setBirthdayDate(LocalDate.parse(this.birthdayDate, DateFormatterUtil.FORMATTER));

        } catch (DateTimeParseException exception) {

            throw new MyDataNotFoundException(
                    "Não foi possível realizar o parse do campo birthdayDate " + this.birthdayDate,
                    this.getClass().getName()
            );
        }
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

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }
}
