package com.tech.titan.satisfactory.api.service;

import com.tech.titan.satisfactory.api.exception.BuildingNotFoundException;
import com.tech.titan.satisfactory.api.model.Building;
import com.tech.titan.satisfactory.api.model.BuildingType;
import com.tech.titan.satisfactory.api.repository.BuildingRepository;

import com.tech.titan.satisfactory.api.service.contract.SearchableService;
import com.tech.titan.satisfactory.api.service.contract.TypeSearchService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService extends SearchableService<Building> implements TypeSearchService<Building, BuildingType> {

    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Building> getAll() {
        return buildingRepository.findAll();
    }

    @Override
    public Building findById(Integer id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @Override
    public Building save(Building entity) {
        return buildingRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        try{
            buildingRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new BuildingNotFoundException(id);
        }
    }

    @Override
    public Building findByName(String name) {
        return buildingRepository.findByName(name)
                .orElseThrow(() -> new BuildingNotFoundException(name));
    }

    @Override
    public List<Building> findAllByType(BuildingType type) {
        return buildingRepository.findAllByBuildingType(type);
    }
}
