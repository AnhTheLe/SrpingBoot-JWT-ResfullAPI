package com.example.springbootjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "product")
public class ProductEntity extends BaseEntity{

    @Column(name = "category")
    private long category;

    @Column(name = "cost")
    private long cost;

    @Column(name = "storage_id")
    private long storageId;

    @Column(name = "intro")
    private String intro;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "sell_quantity")
    private long sellQuantity;


    @ManyToOne
    @JoinColumn(insertable=false, updatable=false,name = "id")
    @JsonIgnore
    private StorageEntity storages;

    @ManyToOne
    @JoinColumn(insertable=false, updatable=false,name = "id")
    @JsonIgnore
    private CategoryTypeEntity categories;

    @Override
    public String toString() {
        return "ProductEntity [id=" + getId() + ", Code=" + getCode() + ", category=" + category + ", cost=" + cost + ", Storage Id=" + storageId + ", Name=" + getName() + ", intro=" + intro + ", Image Link=" + imageLink + ", quantity=" + quantity + ", sellQuantity=" + sellQuantity + ", dateCreate=" + getDateCreate() + ", dateUpdate=" + getDateUpdate() + "]";
    }
}
