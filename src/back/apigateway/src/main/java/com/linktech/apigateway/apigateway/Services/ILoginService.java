package com.linktech.apigateway.apigateway.Services;

import com.linktech.apigateway.apigateway.Models.User;

public interface ILoginService {
    String login(String username, String password);
    User saveUser(User user);

    boolean logout(String token);

    Boolean isValidToken(String token);

    String createNewToken(String token);
}