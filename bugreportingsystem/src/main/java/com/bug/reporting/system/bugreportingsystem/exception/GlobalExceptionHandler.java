package com.bug.reporting.system.bugreportingsystem.exception;

import com.bug.reporting.system.bugreportingsystem.shared.ApiResponse;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ApiResponse status;

    /**
     * Handle SignatureException thrown by JwtService
     * This exception is raised when the signature can not be validated
     *
     * @param exception Exception object
     * @return ResponseEntity with a message in the body
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handleSignatureException(SignatureException exception) {
        CustomMessage errorResponse = new CustomMessage("Invalid JWT signature found, token is invalid");
        return new ResponseEntity<>(errorResponse.toString(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * <p>
     * These method handles exceptions thrown by the controller methods
     * and return a ResponseEntity with a message in the body.
     * The message is a custom
     * </p>
     *
     * @param runtimeException Exception object
     * @return ResponseEntity with a message in the body
     * @see ResponseEntity
     */
    @ExceptionHandler({RuntimeException.class})
    public Map<String, String> handleRuntimeException(RuntimeException runtimeException) {
        Map<String, String> entryMap = new HashMap<>();
        entryMap.put("error:", runtimeException.getMessage());
        return entryMap;
    }


    /**
     * <p>
     * The message is a HashMap with field names as keys and error messages as values.
     * </p>
     *
     * @param ex Exception object
     * @return error message
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ex.printStackTrace();
        status.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(status);
    }

    /**
     * <p>
     * This method handle InvalidUserCredential i.e email password etc
     * </p>
     *
     * @param ex Exception object
     * @return error message
     */
//    @ExceptionHandler(InvalidUserCredentialException.class)
//    public ResponseEntity<Map<String, String>> handleInvalidUserCredentialException(InvalidUserCredentialException ex) {
//        Map<String, String> resp = new HashMap<>();
//        String message= ex.getLocalizedMessage();
//        resp.put("error:", message);
//        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
//    }

    /**
     * <p>
     * This method handle UserAlreadyExist
     * </p>
     *
     * @param ex Exception object
     * @return error message
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        Map<String, String> resp = new HashMap<>();
        String message = ex.getLocalizedMessage();
        resp.put("error:", message);
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUserCredentialException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleInvalidUserCredentialException(InvalidUserCredentialException ex) {
        ex.printStackTrace();
        status.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(status);
    }
}
