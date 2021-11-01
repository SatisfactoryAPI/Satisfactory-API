package com.tech.titan.satisfactory.api.exception.contract;


import com.tech.titan.satisfactory.api.model.Item;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("The requested resource was not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
