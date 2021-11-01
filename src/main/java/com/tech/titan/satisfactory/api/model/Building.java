package com.tech.titan.satisfactory.api.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buildings", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
public class Building implements Serializable {

    private Integer buildingId;
    private String name;
    private BuildingType buildingType;

    public Building() {
    }

    public Building(String name, BuildingType buildingType) {
        this.name = name;
        this.buildingType = buildingType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id", unique = true, nullable = false)
    public Integer getBuildingId(){
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    @Column(name = "name", unique = true, nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "building_type_id", referencedColumnName = "building_type_id")
    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }
}
