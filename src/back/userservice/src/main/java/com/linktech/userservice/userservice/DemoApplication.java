package com.linktech.userservice.userservice;

import com.linktech.userservice.userservice.models.UserModel;
import com.linktech.userservice.userservice.repositories.IUserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

// @EnableEurekaClient
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
	CommandLineRunner init(IUserRepository userRepository){

        return args -> {
			
			UserModel usr = userRepository.findByEmail("MarkAngelo@user.com");
			if (usr == null){
				UserModel user = new UserModel();
				user.setEmail("MarkAngelo@user.com");
				user.setFirstName("Mark");
				user.setSecondName("Angelo");
				userRepository.save(user);
			}
        };
    }
}
