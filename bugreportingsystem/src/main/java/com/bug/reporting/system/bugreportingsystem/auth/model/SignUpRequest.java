package com.bug.reporting.system.bugreportingsystem.auth.model;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotEmpty(message = "lastName is required")
    @JsonProperty("last_name")
    @Size(min = 2, max = 50, message = "lastName must be between 2 and 20 characters")
    private String lastName;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

}