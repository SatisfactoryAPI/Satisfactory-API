package com.tech.titan.satisfactory.api.exception;

import com.tech.titan.satisfactory.api.exception.contract.NotFoundException;
import com.tech.titan.satisfactory.api.model.Building;

public class BuildingNotFoundException extends NotFoundException {

    public BuildingNotFoundException(){

    }

    public BuildingNotFoundException(String name){
        super("Could not find the building with name: " + name);
    }

    public BuildingNotFoundException(Integer buildingId){
        super("Could not find the building with id: " + buildingId);
    }

    public BuildingNotFoundException(Building building){
        super("Could not find the building: " + building);
    }
}
