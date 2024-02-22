package com.bug.reporting.system.bugreportingsystem.auth.controller;

import com.bug.reporting.system.bugreportingsystem.auth.service.AuthenticationService;
import com.bug.reporting.system.bugreportingsystem.auth.model.ChangePasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.ForgetPasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.SignUpRequest;
import com.bug.reporting.system.bugreportingsystem.auth.model.SigninRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200") // Adjust the origin based on your Angular app's URL
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Validated SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        return authenticationService.signin(request);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return authenticationService.changePassword(changePasswordDto);
    }
    @PreAuthorize("\"hasRole('ADMIN')\"")
    @PostMapping("/forget")
    public ResponseEntity<?> forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        return authenticationService.forgetPassword(forgetPasswordDto);
    }
    @PostMapping("/code")
    public String generateCode(@RequestParam String email){
        return authenticationService.generateCode(email);
    }
}
