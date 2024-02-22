package com.bug.reporting.system.bugreportingsystem.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordDto {
    private String email;
    private String newPassword;
}
