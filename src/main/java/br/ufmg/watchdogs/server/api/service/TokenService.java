package br.ufmg.watchdogs.server.api.service;

import br.ufmg.watchdogs.server.api.model.User;
import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication auth);
    boolean isTokenValid(String token);
    User getUserByToken(String token);
}
