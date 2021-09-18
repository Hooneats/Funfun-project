package com.kosmo.funfunhaejwo.jpa.exception;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

public class NotVerifiedTokenException extends TokenExpiredException {
    public NotVerifiedTokenException(String message) {
        super(message);
    }
}
