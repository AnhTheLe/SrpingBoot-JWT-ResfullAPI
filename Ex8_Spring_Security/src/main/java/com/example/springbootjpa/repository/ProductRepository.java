package com.example.springbootjpa.repository;

import com.example.springbootjpa.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    Optional<ProductEntity> findById(Long id);
    Optional<ProductEntity> findProductCodeById(Long id);
    Optional<ProductEntity> deleteAllByStorageId(Long id);
    // Tìm kiếm theo từ khóa
    Page<ProductEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<ProductEntity> findByNameContainingIgnoreCaseAndCategory(String name, Long categoryId, Pageable pageable);
}
