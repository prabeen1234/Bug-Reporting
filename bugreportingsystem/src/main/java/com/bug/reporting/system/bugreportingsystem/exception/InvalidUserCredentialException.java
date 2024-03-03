package com.bug.reporting.system.bugreportingsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUserCredentialException extends RuntimeException{
    public InvalidUserCredentialException(String message) {
        super(message);
    }
}
