package com.bug.reporting.system.bugreportingsystem.auth.service;


import com.bug.reporting.system.bugreportingsystem.auth.config.JwtAuthenticationResponse;
import com.bug.reporting.system.bugreportingsystem.auth.config.JwtService;
import com.bug.reporting.system.bugreportingsystem.auth.config.UserDetailService;
import com.bug.reporting.system.bugreportingsystem.auth.dto.ChangePasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.dto.ForgetPasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.dto.SignUpRequest;
import com.bug.reporting.system.bugreportingsystem.auth.dto.SigninRequest;
import com.bug.reporting.system.bugreportingsystem.auth.entity.Role;
import com.bug.reporting.system.bugreportingsystem.auth.entity.User;
import com.bug.reporting.system.bugreportingsystem.auth.repository.UserRepository;
import com.bug.reporting.system.bugreportingsystem.exception.InvalidUserCredentialException;
import com.bug.reporting.system.bugreportingsystem.exception.UserAlreadyExistException;
import com.bug.reporting.system.bugreportingsystem.exception.UserNotFoundException;
import com.bug.reporting.system.bugreportingsystem.shared.MessageConstant;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to create the login and signup
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoders;
    private final JwtService jwtService;
    private final UserDetailService userService;
    private final JavaMailSender mailSender;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private Integer port;
    @Value("${spring.mail.username}")
    private String mail;

    @Override
    public UserResponse signup(SignUpRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistException(MessageConstant.ALREADY_REGISTER);
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoders.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return new UserResponse(MessageConstant.SUCCESSFULLY_SAVE);
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        if (!passwordEncoders.matches(request.getPassword(), userDetails.getPassword())) {
            throw new InvalidUserCredentialException(MessageConstant.INVALID_EMAIL_AND_PASSWORD_COMBINATION);
        }

        String token = jwtService.generateToken(userDetails);
        return new JwtAuthenticationResponse(token, MessageConstant.SUCCESSFULLY_LOGIN);
    }


    @Override
    public UserResponse changePassword(ChangePasswordDto changePasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
                if (passwordEncoders.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
                    user.setPassword(passwordEncoders.encode(changePasswordDto.getConfirmPassword()));
                    userRepository.save(user);
                    return new UserResponse(MessageConstant.SUCCESSFULLY_UPDATED_THE_PASSWORD);
                }
            }
        }
        throw new UserNotFoundException(MessageConstant.USER_NOT_FOUND);
    }

    public UserResponse generateCode(String email) {
        int min = 100000;
        int max = 999999;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        String forgetPasswordCode = String.valueOf(randomNum);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(mail);
        mailSender.setPassword("srcl yxbs lldm lvtl");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(List.of(email).toString());
        message.setSubject("your password reset code");
        message.setText("your reset password code is:" + forgetPasswordCode);
        mailSender.send(message);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setForgetPasswordCode(forgetPasswordCode);
            user.setForgetPasswordCodeTimestamp(new Date(System.currentTimeMillis()));
            userRepository.save(user);
            return new UserResponse(MessageConstant.SEND_CODE_TO_THE_EMAIL);
        }
        return new UserResponse(MessageConstant.USER_NOT_FOUND);
    }

    @Override
    public UserResponse forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        Optional<User> optionalUser = userRepository.findByForgetPasswordCode(forgetPasswordDto.getCode());
        if (optionalUser.isPresent() && forgetPasswordDto.getConfirmPassword().equals(forgetPasswordDto.getNewPassword())) {
            optionalUser.get().setPassword(passwordEncoders.encode(forgetPasswordDto.getNewPassword()));
            userRepository.save(optionalUser.get());
            return new UserResponse(MessageConstant.SUCCESSFULLY_UPDATED_THE_PASSWORD);
        }
        return new UserResponse(MessageConstant.COMPARE_CODE_NEW_AND_CONFIRM_PASSWORD);
    }

    @Scheduled(fixedDelay = 120000)
    public void clearUpPasswordCode() {
        Timestamp twoMinutesAgo = new Timestamp(System.currentTimeMillis() - 2 * 60 * 1000);
        System.out.println(twoMinutesAgo);
        List<User> user = userRepository.findByForgetPasswordCodeTimestampBefore(twoMinutesAgo);
        for (User listOfUser : user) {
            listOfUser.setForgetPasswordCode(null);
            userRepository.save(listOfUser);
        }
    }
}
