package com.kosmo.funfunhaejwo.jpa.exception;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;

public class NotVerifiedTokenException extends Exception {
    public NotVerifiedTokenException(String message) {
        super(message);
    }
}
