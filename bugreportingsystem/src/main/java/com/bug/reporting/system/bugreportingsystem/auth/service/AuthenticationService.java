package com.bug.reporting.system.bugreportingsystem.auth.service;


import com.bug.reporting.system.bugreportingsystem.auth.model.ChangePasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.ForgetPasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.SignUpRequest;
import com.bug.reporting.system.bugreportingsystem.auth.model.SigninRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> signup(SignUpRequest request);

    ResponseEntity<?> signin(SigninRequest request);

    ResponseEntity<?> changePassword(ChangePasswordDto changePasswordDto);

     ResponseEntity<?> forgetPassword(ForgetPasswordDto forgetPasswordDto);

   String generateCode(String email);


}