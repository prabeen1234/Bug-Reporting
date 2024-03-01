package com.bug.reporting.system.bugreportingsystem.configuration;

import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Rimesh sapkota");
        myContact.setEmail("your.email@gmail.com");

        Info information = new Info()
                .title("Bug Reporting System")
                .version("1.0")
                .description("This API exposes endpoints to manage student.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
