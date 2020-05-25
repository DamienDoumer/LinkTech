package com.linktech.institutionsservice.institutionsservice.ApiClient;

import com.linktech.institutionsservice.institutionsservice.models.ExternalModels.UserModel;

import feign.Param;
import feign.RequestLine;

public interface UserClient {
    @RequestLine("GET /{id}")
    UserModel get(@Param("id") String id);
}