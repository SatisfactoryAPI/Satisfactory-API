package com.tech.titan.satisfactory.api.repository;

import com.tech.titan.satisfactory.api.model.Item;
import com.tech.titan.satisfactory.api.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<Item> findByName(String name);
    List<Item> findAllByItemType(ItemType itemType);
}
