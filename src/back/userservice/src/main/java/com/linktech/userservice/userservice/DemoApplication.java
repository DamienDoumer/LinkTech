package com.linktech.userservice.userservice;

import com.linktech.userservice.userservice.models.UserModel;
import com.linktech.userservice.userservice.repositories.IUserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

// @EnableEurekaClient
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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
