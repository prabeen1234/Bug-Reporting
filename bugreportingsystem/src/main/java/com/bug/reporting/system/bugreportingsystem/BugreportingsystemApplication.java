package com.bug.reporting.system.bugreportingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
public class BugreportingsystemApplication {
	public static void main(String[] args) {
		System.out.println("This is main method");
		SpringApplication.run(BugreportingsystemApplication.class, args);
	}
}
