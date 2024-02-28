package com.bug.reporting.system.bugreportingsystem.exception;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handle SignatureException thrown by JwtService
     * This exception is raised when the signature can not be validated
     * @param exception Exception object
     * @return ResponseEntity with a message in the body
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handleSignatureException(SignatureException exception){
        CustomMessage errorResponse = new CustomMessage("Invalid JWT signature found, token is invalid");
        return new ResponseEntity<>(errorResponse.toString(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * <p>
     *     These method handles exceptions thrown by the controller methods
     *     and return a ResponseEntity with a message in the body.
     *     The message is a custom
     * </p>
     * @param runtimeException Exception object
     * @return ResponseEntity with a message in the body
     * @see ResponseEntity
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException){
        return ResponseEntity.badRequest().body(runtimeException.getMessage());
    }

    /**
     * Handle MethodArgumentNotValidException
     * <p>
     * This method handles MethodArgumentNotValidException thrown by the controller methods
     * and returns a ResponseEntity with a message in the body.
     * The message is a HashMap with field names as keys and error messages as values.
     * </p>
     *
     * @param ex Exception object
     * @return ResponseEntity with a message in the body
     * @see ResponseEntity
     * @see org.springframework.web.bind.MethodArgumentNotValidException
     * @see org.springframework.web.bind.annotation.ExceptionHandler
     */
    @ExceptionHandler({org.springframework.web.bind.MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
    
    /**
     * <p>
     * The message is a HashMap with field names as keys and error messages as values.
     * </p>
     * @param userNotFoundException Exception object
     * @return error message
     */
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFound(UserNotFoundException userNotFoundException) {
        Map<String, String> entryMap = new HashMap<>();
        entryMap.put("error message is", userNotFoundException.getMessage());
        return entryMap;
    }
}
