package com.tech.titan.satisfactory.api.service.contract;

import java.util.List;

public interface SatisfactoryService<T> {

    List<T> getAll();

    T findById(Integer id);

    T save(T entity);

    void deleteById(Integer id);
}
