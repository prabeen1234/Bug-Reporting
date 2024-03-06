package com.bug.reporting.system.bugreportingsystem.auth.service;


import com.bug.reporting.system.bugreportingsystem.auth.config.JwtAuthenticationResponse;
import com.bug.reporting.system.bugreportingsystem.auth.dto.ChangePasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.dto.ForgetPasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.dto.SignUpRequest;
import com.bug.reporting.system.bugreportingsystem.auth.dto.SigninRequest;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;

public interface AuthenticationService {

    UserResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    UserResponse changePassword(ChangePasswordDto changePasswordDto);

    UserResponse forgetPassword(ForgetPasswordDto forgetPasswordDto);

    UserResponse generateCode(String email);


}