package com.tech.titan.satisfactory.api.model;

public enum ItemType {

    RAW_RESOURCE("RAW_RESOURCE"),
    RESOURCE_NODE("RESOURCE_NODE"),
    MANUFACTURED("MANUFACTURED");

    public final String value;

    ItemType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
