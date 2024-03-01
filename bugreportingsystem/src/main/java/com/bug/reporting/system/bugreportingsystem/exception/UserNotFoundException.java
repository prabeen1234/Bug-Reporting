package com.bug.reporting.system.bugreportingsystem.exception;

import lombok.NoArgsConstructor;

import javax.naming.AuthenticationException;

@NoArgsConstructor
public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
