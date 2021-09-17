package com.kosmo.funfunhaejwo.jpa.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ProfileNotFoundException extends UsernameNotFoundException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
