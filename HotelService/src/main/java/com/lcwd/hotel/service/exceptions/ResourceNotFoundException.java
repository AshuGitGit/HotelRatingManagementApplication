package com.lcwd.hotel.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException()
    {
        super("Resource not found on the Server!!");
    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
