package com.bug.reporting.system.bugreportingsystem.auth.controller;

import com.bug.reporting.system.bugreportingsystem.auth.config.JwtAuthenticationResponse;
import com.bug.reporting.system.bugreportingsystem.auth.model.ChangePasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.ForgetPasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.SignUpRequest;
import com.bug.reporting.system.bugreportingsystem.auth.model.SigninRequest;
import com.bug.reporting.system.bugreportingsystem.auth.service.AuthenticationService;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody @Validated SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SigninRequest request) {
        return authenticationService.signin(request);
    }

    @PostMapping("/change_password")
    public UserResponse changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return authenticationService.changePassword(changePasswordDto);
    }

    @PostMapping("/forget_password")
    public UserResponse forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        return authenticationService.forgetPassword(forgetPasswordDto);
    }

    @PostMapping("/generate_code")
    public UserResponse generateCode(@RequestParam String email) {
        return authenticationService.generateCode(email);
    }
}
