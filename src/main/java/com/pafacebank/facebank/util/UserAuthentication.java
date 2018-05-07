package com.pafacebank.facebank.util;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication extends AbstractAuthenticationToken {

    private UserToken currentUser;

    public UserAuthentication(UserToken currentUser, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        setAuthenticated(true);
        this.currentUser = currentUser;
    }

    public UserToken currentUser(){
        return currentUser;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return currentUser;
    }
}
