package com.bug.reporting.system.bugreportingsystem.auth.controller;

import com.bug.reporting.system.bugreportingsystem.auth.config.JwtAuthenticationResponse;
import com.bug.reporting.system.bugreportingsystem.auth.dto.ChangePasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.dto.ForgetPasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.dto.SignUpRequest;
import com.bug.reporting.system.bugreportingsystem.auth.dto.SigninRequest;
import com.bug.reporting.system.bugreportingsystem.auth.service.AuthenticationService;
import com.bug.reporting.system.bugreportingsystem.shared.ApiURL;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(ApiURL.USER_SIGN_UP)
    public UserResponse signup(@RequestBody @Validated SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping(ApiURL.USER_SIGN_IN)
    public JwtAuthenticationResponse signin(@RequestBody SigninRequest request) {
        return authenticationService.signin(request);
    }

    @PostMapping(ApiURL.CHANGE_PASSWORD)
    public UserResponse changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return authenticationService.changePassword(changePasswordDto);
    }

    @PostMapping(ApiURL.FORGET_PASSWORD)
    public UserResponse forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        return authenticationService.forgetPassword(forgetPasswordDto);
    }

    @PostMapping(ApiURL.CODE_GENERATE)
    public UserResponse generateCode(@RequestParam String email) {
        return authenticationService.generateCode(email);
    }
}
