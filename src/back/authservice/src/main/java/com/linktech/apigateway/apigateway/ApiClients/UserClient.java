package com.linktech.apigateway.apigateway.ApiClients;

import com.linktech.apigateway.apigateway.Models.ExternalModels.UserModel;

import feign.Headers;
import feign.RequestLine;

public interface UserClient {

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    UserModel Create(UserModel user);
}