package com.linktech.apigateway.apigateway.Services;

import com.linktech.apigateway.apigateway.Models.MongoUserDetails;
import com.linktech.apigateway.apigateway.Models.Role;
import com.linktech.apigateway.apigateway.Models.User;
import com.linktech.apigateway.apigateway.Repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        
        if (user == null) {
            throw new RuntimeException("Invalid username or password: "+ HttpStatus.UNAUTHORIZED);
        }
        
        MongoUserDetails userDetails = new MongoUserDetails(user.getEmail(),user.getPassword(),user.getActive(),
                user.isLoacked(), user.isExpired(),user.isEnabled());
        return userDetails;
    }
}
