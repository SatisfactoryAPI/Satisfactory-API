package com.tech.titan.satisfactory.api.exception;

import com.tech.titan.satisfactory.api.exception.contract.NotFoundException;
import com.tech.titan.satisfactory.api.model.Item;

public class ItemNotFoundException extends NotFoundException {

    public ItemNotFoundException(){

    }

    public ItemNotFoundException(String name){
        super("Could not find the item with name: " + name);
    }

    public ItemNotFoundException(Integer itemId){
        super("Could not find the item with id: " + itemId);
    }

    public ItemNotFoundException(Item item){
        super("Could not find the item: " + item);
    }
}
