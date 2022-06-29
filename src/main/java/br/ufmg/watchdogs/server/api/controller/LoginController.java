package br.ufmg.watchdogs.server.api.controller;

import br.ufmg.watchdogs.server.api.model.dto.LoginDto;
import br.ufmg.watchdogs.server.api.model.form.LoginForm;
import br.ufmg.watchdogs.server.api.model.User;
import br.ufmg.watchdogs.server.api.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class LoginController {

    private final TokenServiceImpl tokenService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(TokenServiceImpl tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("login")
    public ResponseEntity<LoginDto> login(@RequestBody @Valid LoginForm loginForm) {

        try {

            Authentication auth = this.authenticationManager.authenticate(loginForm.convertToUsernamePasswordAuthenticationToken());
            String token = tokenService.generateToken(auth);

            return ResponseEntity.ok(new LoginDto(
                    (User) auth.getPrincipal(),
                    token
            ));

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
