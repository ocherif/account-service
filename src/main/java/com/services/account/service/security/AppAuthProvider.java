package com.services.account.service.seucrity;

import com.services.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class AppAuthProvider extends DaoAuthenticationProvider {
    @Autowired
    UserService userDetailsService;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        String password = auth.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(name);

        if (Objects.isNull(user)) {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}