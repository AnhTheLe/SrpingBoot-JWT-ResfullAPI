package com.example.springbootjpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "storage")
@Getter
@Setter
public class StorageEntity extends BaseEntity {

    @Column(name = "storage_address")
    private String address;
    @OneToMany(mappedBy = "storages", cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    public List<ProductEntity> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "StorageEntity [id=" + getId() + ", Code=" + getCode() + ", Name=" + getName() + ", address=" + address + ", Date Create=" + getDateCreate() + ", Date Update=" + getDateUpdate() + "]";
    }
}
