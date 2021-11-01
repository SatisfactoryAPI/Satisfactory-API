package com.tech.titan.satisfactory.api.controller;

import com.tech.titan.satisfactory.api.exception.ItemNotFoundException;
import com.tech.titan.satisfactory.api.model.Item;
import com.tech.titan.satisfactory.api.model.ItemType;
import com.tech.titan.satisfactory.api.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController()
@CrossOrigin("*")
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Integer id){
        return itemService.findItemById(id);
    }

    @GetMapping("/types")
    public List<ItemType> getAllItemTypes(){
        return Arrays.asList(ItemType.values());
    }

    @GetMapping("/types/{itemType}")
    public List<Item> getAllByItemType(@PathVariable String itemType){
        return itemService.findAllByItemType(ItemType.valueOf(itemType.toUpperCase()));
    }

    @GetMapping("/names/{name}")
    public Item getItemByName(@PathVariable String name){
        return itemService.findItemByName(name);
    }

    @PostMapping
    public Item createNewItem(@RequestBody Item newItem){
        return itemService.saveItem(newItem);
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item){
        return itemService.saveItem(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable Integer id){
        itemService.deleteItemById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
