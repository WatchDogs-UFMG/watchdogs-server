package br.ufmg.watchdogs.server.api.config.security;

import br.ufmg.watchdogs.server.api.filter.TokenAuthenticationFilter;
import br.ufmg.watchdogs.server.api.service.TokenService;
import br.ufmg.watchdogs.server.api.service.impl.AuthenticationServiceImpl;
import br.ufmg.watchdogs.server.api.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;
    private final UserDetailsService authenticationService;

    @Autowired
    public WebSecurityConfig(
            TokenServiceImpl tokenService,
            AuthenticationServiceImpl authenticationService
    ) {
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v1/profile").hasAnyRole("DEV", "ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/user").hasAnyRole("DEV", "ADMIN")
                .antMatchers(HttpMethod.POST, "/v1/profile").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/login").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/user").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new TokenAuthenticationFilter(this.tokenService), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/**.html",
                "/v2/api-docs",
                "/webjars/**",
                "/configuration/**",
                "/swagger-resources/**"
        );
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
