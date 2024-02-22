package com.bug.reporting.system.bugreportingsystem.auth.model;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotNull(message = "firstName is required")
    @NotEmpty(message = "firstName is required")
    @Size(min = 2, max = 50, message = "firstName must be between 2 and 20 characters")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotEmpty(message = "lastName is required")
    @Size(min = 2, max = 50, message = "lastName must be between 2 and 20 characters")
    private String lastName;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = "^(?!.*\\s).*$", message = "Password cannot contain spaces")
    @Pattern(regexp = "^(?=.*[a-z]).*$", message = "Password must contain at least one lowercase letter")
    @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter")
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "Password must contain at least one number")
    @Pattern(regexp = "^(?=.*[!@#$%^&*]).*$", message = "Password must contain at least one special character")
    private String password;

//    public User touser(SignUpRequest sign) {
//        User user = new User();
//        user.setFirstName(sign.getFirstName());
//        user.setLastName(sign.getLastName());
//        user.setEmail(sign.getEmail());
//        user.setPassword(user.getPassword());
//        user.setRole(Role.USER);
//        return user;
//    }
}