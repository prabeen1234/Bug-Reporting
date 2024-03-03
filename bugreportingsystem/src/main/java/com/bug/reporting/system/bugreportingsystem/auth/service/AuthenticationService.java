package com.bug.reporting.system.bugreportingsystem.auth.service;


import com.bug.reporting.system.bugreportingsystem.auth.config.JwtAuthenticationResponse;
import com.bug.reporting.system.bugreportingsystem.auth.model.ChangePasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.ForgetPasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.SignUpRequest;
import com.bug.reporting.system.bugreportingsystem.auth.model.SigninRequest;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;

public interface AuthenticationService {

    UserResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    UserResponse changePassword(ChangePasswordDto changePasswordDto);

    UserResponse forgetPassword(ForgetPasswordDto forgetPasswordDto);

    UserResponse generateCode(String email);


}