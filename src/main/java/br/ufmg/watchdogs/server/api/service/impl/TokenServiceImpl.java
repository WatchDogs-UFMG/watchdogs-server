package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.exception.MyInvalidTokenException;
import br.ufmg.watchdogs.server.api.model.User;
import br.ufmg.watchdogs.server.api.service.TokenService;
import br.ufmg.watchdogs.server.api.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private final String SECRET = "A(RF*R_3cb0j3A0";
    private final long EXPIRATION = 86400000L; // 1 Day

    private final UserService userService;

    @Autowired
    public TokenServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public String generateToken(Authentication auth) {

        User user = (User) auth.getPrincipal();

        return Jwts.builder()
                .setIssuer("WatchDogs Server API /login endpoint")
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, this.SECRET)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {

        try {

            Jwts.parser()
                    .setSigningKey(this.SECRET)
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getUserByToken(String token) {

        try {

            Long userId = Long.parseLong(
                    Jwts.parser()
                            .setSigningKey(this.SECRET)
                            .parseClaimsJws(token)
                            .getBody()
                            .getSubject()
            );

            return this.userService.findById(userId);

        } catch (Exception e) {
            throw new MyInvalidTokenException(
                    "Não foi possível realizar o parse do token!",
                    this.getClass().getName()
            );
        }
    }
}
