package br.ufmg.watchdogs.server.dto;

import br.ufmg.watchdogs.server.model.User;

public class LoginDto {

    private Long id;
    private String role;
    private String username;
    private String email;
    private String name;
    private String token;

    public LoginDto(User user, String token) {
        this.id = user.getId();
        this.role = user.getProfile().getRole().toString();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
