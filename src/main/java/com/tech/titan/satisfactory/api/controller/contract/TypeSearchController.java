package com.tech.titan.satisfactory.api.controller.contract;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TypeSearchController<T, E> {
    @GetMapping("/types/{type}")
    List<T> getAllByType(@PathVariable String type);

    @GetMapping("/types")
    List<E> getAllTypes();
}
