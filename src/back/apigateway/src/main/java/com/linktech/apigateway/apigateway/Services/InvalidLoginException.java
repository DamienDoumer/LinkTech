package com.linktech.apigateway.apigateway.Services;

public class InvalidLoginException extends Exception{
 
    public InvalidLoginException(String message)
    {
        super(message);
    }
}