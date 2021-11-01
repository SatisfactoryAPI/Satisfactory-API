package com.tech.titan.satisfactory.api.controller;

import com.tech.titan.satisfactory.api.controller.contract.SearchableController;
import com.tech.titan.satisfactory.api.controller.contract.TypeSearchController;
import com.tech.titan.satisfactory.api.model.Building;
import com.tech.titan.satisfactory.api.model.BuildingType;
import com.tech.titan.satisfactory.api.service.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController()
@CrossOrigin("*")
@RequestMapping("/buildings")
public class BuildingController extends SearchableController<Building> implements TypeSearchController<Building, BuildingType> {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService){
        this.buildingService = buildingService;
    }

    @Override
    public Building getByName(String name) {
        return buildingService.findByName(name);
    }

    @Override
    public List<Building> getAll() {
        return buildingService.getAll();
    }

    @Override
    public Building getById(Integer id) {
        return buildingService.findById(id);
    }

    @Override
    public Building create(Building newEntity) {
        return buildingService.save(newEntity);
    }

    @Override
    public Building update(Building entity) {
        return buildingService.save(entity);
    }

    @Override
    public ResponseEntity<?> deleteById(Integer id) {
        buildingService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public List<Building> getAllByType(String type) {
        return buildingService.findAllByType(BuildingType.valueOf(type.toUpperCase()));
    }

    @Override
    public List<BuildingType> getAllTypes() {
        return Arrays.asList(BuildingType.values());
    }
}
