package com.tech.titan.satisfactory.api.controller.contract;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface SatisfactoryRestController<T> {

    @GetMapping
    List<T> getAll();

    @GetMapping("/{id}")
    T getById(@PathVariable Integer id);

    @PostMapping
    T create(@RequestBody T newEntity);

    @PutMapping
    T update(@RequestBody T entity);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable Integer id);
}
