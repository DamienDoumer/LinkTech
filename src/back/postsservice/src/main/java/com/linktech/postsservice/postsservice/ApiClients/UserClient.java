package com.linktech.postsservice.postsservice.ApiClients;

import com.linktech.postsservice.postsservice.models.ExternalModels.UserModel;

import feign.Param;
import feign.RequestLine;

public interface UserClient {
    @RequestLine("GET /{id}")
    UserModel get(@Param("id") String id);
}