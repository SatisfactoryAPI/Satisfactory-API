package com.tech.titan.satisfactory.api.service.contract;

import java.util.List;

public interface TypeSearchService<T,E> {
    List<T> findAllByType(E type);
}
