package com.tech.titan.satisfactory.api.controller;

import com.tech.titan.satisfactory.api.controller.contract.SearchableController;
import com.tech.titan.satisfactory.api.controller.contract.TypeSearchController;
import com.tech.titan.satisfactory.api.model.Item;
import com.tech.titan.satisfactory.api.model.ItemType;
import com.tech.titan.satisfactory.api.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController()
@CrossOrigin("*")
@RequestMapping("/items")
public class ItemController extends SearchableController<Item> implements TypeSearchController<Item, ItemType> {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @Override
    public Item getByName(String name) {
        return itemService.findByName(name);
    }

    @Override
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @Override
    public Item getById(Integer id) {
        return itemService.findById(id);
    }

    @Override
    public Item create(Item newEntity) {
        return itemService.save(newEntity);
    }

    @Override
    public Item update(Item entity) {
        return itemService.save(entity);
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        itemService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public List<Item> getAllByType(String type) {
        return itemService.findAllByType(ItemType.valueOf(type.toUpperCase()));
    }

    @Override
    public List<ItemType> getAllTypes() {
        return Arrays.asList(ItemType.values());
    }
}
