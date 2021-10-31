package com.tech.titan.satisfactory.api.model;

public enum BuildingType {

    HARVESTING("HARVESTING"),
    MANUFACTURING("MANUFACTURING");

    public final String value;

    BuildingType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}

