package com.company.security.jwt.model;

import com.company.security.jwt.service.UserDetailsServiceImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    private final UserDetailsServiceImpl userDetailsService;

    public CurrentUser(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public JwtUser getCurrentUser(){
        return (JwtUser) userDetailsService.loadUserByUsername(getCurrent());
    }

    private static String getCurrent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() !=null){
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
