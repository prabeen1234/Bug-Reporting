package com.bug.reporting.system.bugreportingsystem.shared;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class ApiResponse implements Serializable {
    protected String message;
    private String operation;
    
}