package com.bug.reporting.system.bugreportingsystem.bootstrap;

import com.bug.reporting.system.bugreportingsystem.auth.entity.Role;
import com.bug.reporting.system.bugreportingsystem.auth.entity.User;
import com.bug.reporting.system.bugreportingsystem.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SuperAdmin implements CommandLineRunner {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        long superAdminCount = userRepository.countByRole(Role.SUPER_ADMIN);
        if (superAdminCount == 0) {
            System.out.println("rimesh sapkota");
            User user = new User();
            user.setFirstName("Rimesh");
            user.setLastName("Sapkota");
            user.setEmail("rimeshsapkota123@gmail.com");
            user.setPassword(passwordEncoder.encode("Rimesh12345@@"));
            user.setRole(Role.SUPER_ADMIN);
            userRepository.save(user);
        }
    }
}
