package com.tech.titan.satisfactory.api.controller.contract;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface NameSearchController<T> {
    @GetMapping("/names/{name}")
    EntityModel<T> getByName(@PathVariable String name);
}
