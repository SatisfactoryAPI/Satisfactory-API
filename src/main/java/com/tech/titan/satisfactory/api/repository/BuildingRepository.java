package com.tech.titan.satisfactory.api.repository;

import com.tech.titan.satisfactory.api.model.Building;
import com.tech.titan.satisfactory.api.model.BuildingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
    Optional<Building> findByName(String name);
    List<Building> findAllByBuildingType(BuildingType buildingType);
}
