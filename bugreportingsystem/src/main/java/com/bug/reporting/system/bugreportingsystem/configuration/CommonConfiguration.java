package com.bug.reporting.system.bugreportingsystem.configuration;

import com.bug.reporting.system.bugreportingsystem.shared.ApiResponse;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.List;

@Configuration
public class CommonConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserResponse userResponse(){
        return UserResponse.builder()
                .message("Error in bug reporting system")
                .build();
    }

    @Bean
    public JavaMailSender mailSender() {
        return new JavaMailSenderImpl();
    }

    //for swagger config
//    @Bean
//    public OpenAPI defineOpenApi() {
//        Server server = new Server();
//        server.setUrl("http://localhost:8080");
//        server.setDescription("Development");
//
//        Contact myContact = new Contact();
//        myContact.setName("Rimesh sapkota");
//        myContact.setEmail("your.email@gmail.com");
//
//        Info information = new Info()
//                .title("Bug Reporting System")
//                .version("1.0")
//                .description("This API exposes endpoints to manage student.")
//                .contact(myContact);
//
//
//        return new OpenAPI().info(information).servers(List.of(server));
//    }
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("Bug Reporting System")
                        .description("Some custom description of API.")
                        .version("1.0").contact(new Contact().name("rimesh sapkota")
                                .email( "www.bugreporting.com").url("bug.com"))
                        .license(new License().name("License of API")
                                .url("API license URL")));
    }
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
    @Bean
    public ApiResponse getStatus() {
        return ApiResponse.builder()
                .message("Exception occured in bug reporting system")
                .operation("Unknown Operation")
                .build();
    }
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
