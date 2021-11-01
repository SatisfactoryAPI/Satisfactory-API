package com.tech.titan.satisfactory.api.service;

import com.tech.titan.satisfactory.api.exception.ItemNotFoundException;
import com.tech.titan.satisfactory.api.model.Item;
import com.tech.titan.satisfactory.api.model.ItemType;
import com.tech.titan.satisfactory.api.repository.ItemRepository;
import com.tech.titan.satisfactory.api.service.contract.SearchableService;
import com.tech.titan.satisfactory.api.service.contract.TypeSearchService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService extends SearchableService<Item> implements TypeSearchService<Item, ItemType> {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Integer id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public Item save(Item entity){
        return itemRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id){
        try{
            itemRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new ItemNotFoundException(id);
        }
    }

    @Override
    public Item findByName(String name){
        return itemRepository.findByName(name)
                .orElseThrow(() -> new ItemNotFoundException(name));
    }

    @Override
    public List<Item> findAllByType(ItemType type){
        return itemRepository.findAllByItemType(type);
    }


}
