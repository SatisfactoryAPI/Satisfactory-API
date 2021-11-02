package com.tech.titan.satisfactory.api.controller.contract;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface TypeSearchController<T, E> {
    @GetMapping("/types/{type}")
    CollectionModel<T> getAllByType(@PathVariable String type);

    @GetMapping("/types")
    CollectionModel<E> getAllTypes();
}
