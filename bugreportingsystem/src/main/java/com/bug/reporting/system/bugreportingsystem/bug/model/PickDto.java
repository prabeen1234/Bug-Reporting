package com.bug.reporting.system.bugreportingsystem.bug.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PickDto {
    private String userEmail;
    private String adminEmail;
}
