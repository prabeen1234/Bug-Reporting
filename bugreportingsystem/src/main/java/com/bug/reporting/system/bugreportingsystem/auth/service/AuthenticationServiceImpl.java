package com.bug.reporting.system.bugreportingsystem.auth.service;


import com.bug.reporting.system.bugreportingsystem.auth.config.JwtAuthenticationResponse;
import com.bug.reporting.system.bugreportingsystem.auth.config.JwtService;
import com.bug.reporting.system.bugreportingsystem.auth.config.UserDetailService;
import com.bug.reporting.system.bugreportingsystem.auth.entity.Role;
import com.bug.reporting.system.bugreportingsystem.auth.entity.User;
import com.bug.reporting.system.bugreportingsystem.auth.model.ChangePasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.ForgetPasswordDto;
import com.bug.reporting.system.bugreportingsystem.auth.model.SignUpRequest;
import com.bug.reporting.system.bugreportingsystem.auth.model.SigninRequest;
import com.bug.reporting.system.bugreportingsystem.auth.repository.UserRepository;
import com.bug.reporting.system.bugreportingsystem.shared.MessageConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public ResponseEntity<?> signup(SignUpRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().body(MessageConstant.alreadyRegisterUser);
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoders.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return ResponseEntity.ok().body(MessageConstant.userRegister);

    }

    @Override
    public ResponseEntity<?> signin(SigninRequest request) {
        try {
            UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
            if (userDetails == null) {
                log.info("User not found logging");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( MessageConstant.userNotFound);
            }

            if (!passwordEncoders.matches(request.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageConstant.password);
            }

            String token = jwtService.generateToken(userDetails);
            JwtAuthenticationResponse jwt = new JwtAuthenticationResponse(token);
            return ResponseEntity.ok(jwt);
        } catch (UsernameNotFoundException ex) {
            log.error("UsernameNotFoundException: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageConstant.userNotFound);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageConstant.internalServerError);
        }
    }

    @Override
    public ResponseEntity<?> changePassword(ChangePasswordDto changePasswordDto) {
        Optional<User> optionalUser = userRepository.findByEmail(changePasswordDto.getEmail());
        if (optionalUser.isPresent()) {
            if (passwordEncoders.matches(changePasswordDto.getNewPassword(), optionalUser.get().getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageConstant.compareOldAndNewPassword);
            }
            User user = optionalUser.get();
            user.setPassword(passwordEncoders.encode(changePasswordDto.getNewPassword()));
            userRepository.save(user);
            return ResponseEntity.ok().body(MessageConstant.updatedPassword);
        }
        return ResponseEntity.badRequest().body(MessageConstant.userNotFound);
    }

    public String generateCode(String email) {
        int min = 100000;
        int max = 999999;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        String forgetPasswordCode = String.valueOf(randomNum);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("rimeshsapkota12345@gmail.com");
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
            log.info("this donot work");
            User user = optionalUser.get();
            user.setForgetPasswordCode(forgetPasswordCode);
            user.setForgetPasswordCodeTimestamp(new Date(System.currentTimeMillis()));
            userRepository.save(user);
            return forgetPasswordCode;
        }
        return "user is not present";
    }

    @Override
    public ResponseEntity<?> forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        Optional<User> optionalUser = userRepository.findByForgetPasswordCode(forgetPasswordDto.getCode());
        if (optionalUser.isPresent() && forgetPasswordDto.getConfirmPassword().equals(forgetPasswordDto.getNewPassword())) {
            optionalUser.get().setPassword(passwordEncoders.encode(forgetPasswordDto.getNewPassword()));
            userRepository.save(optionalUser.get());
            return ResponseEntity.ok().body(MessageConstant.updatedPassword);
        }
        return ResponseEntity.badRequest().body(MessageConstant.compareCodeNewAndConfirmPassword);
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
