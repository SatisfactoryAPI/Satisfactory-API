package com.tech.titan.satisfactory.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "items", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
public class Item implements Serializable {

    private Integer itemId;
    private String name;
    private ItemType itemType;

    public Item(){

    }

    public Item(String itemName) {
        this.name = itemName;
    }

    public Item(String itemName, ItemType itemType) {
        this.name = itemName;
        this.itemType = itemType;
    }

    public Item(Integer itemId, String name, ItemType itemType) {
        this.itemId = itemId;
        this.name = name;
        this.itemType = itemType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", unique = true, nullable = false)
    public Integer getItemId(){
        return this.itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Column(name="name", unique = true, nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "item_type_id", referencedColumnName = "item_type_id")
    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", itemType=" + itemType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(name, item.name) && itemType == item.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, itemType);
    }
}
