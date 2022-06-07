package br.ufmg.watchdogs.server.api.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginForm {

    @NotBlank(message = "Esse campo deve ser preenchido")
    @Email(message = "Forneça um email válido")
    private String email;

    @NotBlank(message = "Esse campo deve ser preenchido")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$", message = "A senha deve conter apenas letras e números")
    @Size(min = 8, max = 16, message = "A senha deve conter entre 8 e 16 caracteres")
    private String password;


    public LoginForm() {
    }

    public Authentication convertToUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
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
}
