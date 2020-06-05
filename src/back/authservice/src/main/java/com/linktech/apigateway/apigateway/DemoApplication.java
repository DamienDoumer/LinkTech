package com.linktech.apigateway.apigateway;

import java.util.Collections;

import com.linktech.apigateway.apigateway.Models.Role;
import com.linktech.apigateway.apigateway.Models.User;
import com.linktech.apigateway.apigateway.Repositories.IRoleRepository;
import com.linktech.apigateway.apigateway.Repositories.IUserRepository;
import com.linktech.apigateway.apigateway.Services.UsersService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
    @Bean
    public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.setAllowedOrigins(Collections.singletonList("*"));
    config.setAllowedHeaders(Collections.singletonList("*"));
    config.setAllowedMethods(Collections.singletonList("*"));
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
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
