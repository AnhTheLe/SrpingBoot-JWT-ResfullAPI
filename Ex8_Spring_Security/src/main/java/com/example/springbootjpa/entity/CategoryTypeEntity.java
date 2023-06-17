package com.example.springbootjpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name = "category_type")
public class CategoryTypeEntity extends BaseEntity {

    @Column(name = "Intro")
    private String intro;

    @OneToMany(mappedBy="categories" , cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    @Override
    public String toString() {
        return "CategoryTypeEntity [id=" + getId() + ", Code=" + getCode() + ", Name=" + getName() + ", intro=" + intro + ", Date Create=" + getDateCreate() + ", Date Update=" + getDateUpdate() + "]";
    }
}
