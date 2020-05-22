package com.linktech.apigateway.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.linktech.apigateway.apigateway.Models.Role;
import com.linktech.apigateway.apigateway.Models.User;
import com.linktech.apigateway.apigateway.Repositories.IRoleRepository;
import com.linktech.apigateway.apigateway.Repositories.IUserRepository;
import com.linktech.apigateway.apigateway.Services.UsersService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
// @EnableEurekaServer
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
    @Bean
	CommandLineRunner init(IRoleRepository roleRepository, IUserRepository userRepository, 
		UsersService usersService){

        return args -> {

            Role adminRole = roleRepository.findByRole(Role.ADMIN_ROLE);
            if (adminRole == null) { 
                Role newAdminRole = new Role();
                newAdminRole.setRole(Role.ADMIN_ROLE);
                roleRepository.save(newAdminRole);
            }
            
            Role userRole = roleRepository.findByRole(Role.USER_ROLE);
            if (userRole == null) {
                Role newUserRole = new Role();
                newUserRole.setRole(Role.USER_ROLE);
                roleRepository.save(newUserRole);
			}
			
			User usr = userRepository.findByEmail("MarkAngelo@user.com");
			if (usr == null){
				User user = new User();
				user.setEmail("MarkAngelo@user.com");
				user.setName("Mark");
				user.setLastName("Angelo");
				user.setPassword("password");
				usersService.saveAdminUser(user);
			}
        };
    }
}
