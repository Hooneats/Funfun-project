package com.kosmo.funfunhaejwo.jpa.exception;

import java.sql.SQLException;

public class PresentMemberEcception extends SQLException {
    public PresentMemberEcception(String message) {
        super(message);
    }
}
