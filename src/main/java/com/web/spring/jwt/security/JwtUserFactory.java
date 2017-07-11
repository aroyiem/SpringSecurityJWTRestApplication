package com.web.spring.jwt.security;

import com.web.spring.jwt.entities.AppUser;
import java.util.ArrayList;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {
    
    private static final Logger LOGGER = Logger.getLogger(JwtUserFactory.class);

    private JwtUserFactory() {
    }

    public static JwtUser create(AppUser user) {
        return new JwtUser(
                user.getId(),
                user.getSsoId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    
    private static List<GrantedAuthority> mapToGrantedAuthorities(AppUser user){
        List<GrantedAuthority> authorities = new ArrayList<>();
         
        user.getUserProfileList().stream().map((userProfile) -> {
            LOGGER.info("UserProfile : {} "+ userProfile);
            return userProfile;
        }).forEachOrdered((userProfile) -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        });
        LOGGER.info("authorities : {}" + authorities);
        return authorities;
    }
}
