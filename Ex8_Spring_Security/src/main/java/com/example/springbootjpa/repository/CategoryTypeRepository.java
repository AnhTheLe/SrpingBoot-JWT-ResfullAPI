package com.example.springbootjpa.repository;

import com.example.springbootjpa.entity.CategoryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryTypeEntity, Long> {

    Optional<CategoryTypeEntity> findById(Long id);
    Optional<CategoryTypeEntity> findByNameContainingIgnoreCase(String name);
}
