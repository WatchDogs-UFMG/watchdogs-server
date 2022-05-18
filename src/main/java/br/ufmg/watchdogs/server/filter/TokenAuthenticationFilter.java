package br.ufmg.watchdogs.server.filter;

import br.ufmg.watchdogs.server.model.User;
import br.ufmg.watchdogs.server.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public TokenAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = this.getToken(request);

        if (tokenService.isTokenValid(token)) {
            this.doUserAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }

    private void doUserAuthentication(String token) {

        User user = this.tokenService.getUserByToken(token);

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                ));
    }
}
