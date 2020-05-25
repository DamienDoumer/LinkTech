package com.linktech.apigateway.apigateway.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.linktech.apigateway.apigateway.ApiClients.UserClient;
import com.linktech.apigateway.apigateway.Models.AuthenticationRequest;
import com.linktech.apigateway.apigateway.Models.AuthenticationResponse;
import com.linktech.apigateway.apigateway.Models.Role;
import com.linktech.apigateway.apigateway.Models.SignupRequest;
import com.linktech.apigateway.apigateway.Models.User;
import com.linktech.apigateway.apigateway.Models.ExternalModels.UserModel;
import com.linktech.apigateway.apigateway.Repositories.IRoleRepository;
import com.linktech.apigateway.apigateway.Repositories.IUserRepository;
import com.linktech.apigateway.apigateway.Services.JwtUtil;
// import com.netflix.discovery.converters.Auto;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
    PasswordEncoder encoder;
    @Autowired
    IRoleRepository roleRepository;
    
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<?> firstPage() {
		return ResponseEntity.ok("Hello World");
    }
    
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
       
		try {
			    authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			    );

                final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
                final String jwt = jwtTokenUtil.generateToken(userDetails);
    
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
        }
        catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
    
    @RequestMapping(value = "/addToAdminRole", method = RequestMethod.POST)
    public ResponseEntity<?> addToAdmin(@RequestBody String email) throws Exception {
    
        if (!userRepository.existsByEmail(email)){
            return ResponseEntity
                    .badRequest()
                    .body("Error: User doesnot exist!");
        }

        User user = userRepository.findByEmail(email);
        
        Role userRole = roleRepository.findByRole(Role.ADMIN_ROLE);
        user.setRole(new HashSet<>(Arrays.asList(userRole)));
        user = userRepository.save(user);

        return ResponseEntity.ok("Added as admin");
    }

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) throws Exception {
       
		try {
            if (userRepository.existsByEmail(signupRequest.getEmail())){
                return ResponseEntity
                        .badRequest()
                        .body("Error: Email is already in use!");
            }

            UserClient userClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(UserClient.class))
                .target(UserClient.class, "http://localhost:2202/users");

            User user = new User();
            user.setEmail(signupRequest.getEmail());
            user.setPassword(encoder.encode(signupRequest.getPassword()));
            user.setName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());

            Role userRole = roleRepository.findByRole(Role.USER_ROLE);
            user.setRole(new HashSet<>(Arrays.asList(userRole)));

            user = userRepository.save(user);
            
			authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signupRequest.getEmail(),
                signupRequest.getPassword()));

		    final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getEmail());

            final String jwt = jwtTokenUtil.generateToken(userDetails);

            UserModel userServiceUser = new UserModel();
            userServiceUser.email = user.getEmail();
            userServiceUser.firstName = user.getName();
            userServiceUser.secondName = user.getLastName();

            UserModel userm = userClient.Create(userServiceUser);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
		}
		catch (BadCredentialsException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Error creating new user");
		}
        catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}