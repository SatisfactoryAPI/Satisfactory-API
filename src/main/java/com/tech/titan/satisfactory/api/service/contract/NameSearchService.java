package com.tech.titan.satisfactory.api.service.contract;

public interface NameSearchService<T>{
    T findByName(String name);
}
