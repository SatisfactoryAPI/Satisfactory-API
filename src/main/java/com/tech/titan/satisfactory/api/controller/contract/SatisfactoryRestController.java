package com.tech.titan.satisfactory.api.controller.contract;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SatisfactoryRestController<T> {

    @GetMapping
    CollectionModel<T> getAll();

    @GetMapping("/{id}")
    EntityModel<T> getById(@PathVariable Integer id);

    @PostMapping
    EntityModel<T> create(@RequestBody T newEntity);

    @PutMapping
    EntityModel<T> update(@RequestBody T entity);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable Integer id);
}
