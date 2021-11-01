package com.tech.titan.satisfactory.api.service;

import com.tech.titan.satisfactory.api.exception.ItemNotFoundException;
import com.tech.titan.satisfactory.api.model.Item;
import com.tech.titan.satisfactory.api.model.ItemType;
import com.tech.titan.satisfactory.api.repository.ItemRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item findItemById(Integer itemId){
        return itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    public Item findItemByName(String itemName){
        return itemRepository.findByName(itemName).orElseThrow(() -> new ItemNotFoundException(itemName));
    }

    public List<Item> findAllByItemType(ItemType itemType){
        return itemRepository.findAllByItemType(itemType);
    }

    public void deleteItemById(Integer itemId){
        try{
            itemRepository.deleteById(itemId);
        } catch(EmptyResultDataAccessException e){
            throw new ItemNotFoundException(itemId);
        }

    }
}
