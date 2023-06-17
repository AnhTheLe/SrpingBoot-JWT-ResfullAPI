package com.example.springbootjpa.service.impl;

import com.example.springbootjpa.dto.CategoryDto;
import com.example.springbootjpa.entity.CategoryTypeEntity;
import com.example.springbootjpa.repository.CategoryTypeRepository;
import com.example.springbootjpa.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryTypeRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapperCategory;

    /**
     * Save or update storage
     * @param categoryDto
     * @return
     */
    public CategoryDto save(CategoryDto categoryDto) {
        CategoryTypeEntity categoryTypeEntity = new CategoryTypeEntity();
        if(categoryDto.getId() != null ){
            CategoryDto finalCategoryDto = categoryDto;
            categoryTypeEntity = categoryRepository.findById(categoryDto.getId())
                    .orElseThrow(() -> new RuntimeException("Storage not found with id: " + finalCategoryDto.getId()));
            modelMapperCategory.map(categoryDto, categoryTypeEntity);
        }else{
            categoryTypeEntity = modelMapperCategory.map(categoryDto, CategoryTypeEntity.class);
        }
        categoryTypeEntity = categoryRepository.save(categoryTypeEntity);
        return modelMapperCategory.map(categoryTypeEntity, CategoryDto.class);
    }

    /**
     * find a storage by id
     * @param id
     * @return
     */
    public CategoryDto findById(Long id) {
        CategoryTypeEntity CategoryTypeEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storage not found with id: " + id));
        return modelMapperCategory.map(CategoryTypeEntity, CategoryDto.class);
    }

    /**
     * Find all storage
     * @return
     */
    public Map<String, Object> getAllCategories(int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by(
                        Sort.Order.asc("id")
                )
        );
        Page<CategoryTypeEntity> pageCategory;
        List<CategoryTypeEntity> categories = new ArrayList<CategoryTypeEntity>();
        pageCategory = categoryRepository.findAll(paging);
        categories = pageCategory.getContent();
        // Mapping qua Dto
        Map<String, Object> response = new HashMap<>();
        List<CategoryDto> categoryDto = Arrays.asList(modelMapperCategory.map(categories, CategoryDto[].class));
        response.put("products", categoryDto);
        response.put("currentPage", pageCategory.getNumber());
        response.put("totalItems", pageCategory.getTotalElements());
        response.put("totalPages", pageCategory.getTotalPages());
        log.info("Get all categories successfully");
        return response;
    }

    /**
     * Delete a list categories by list id, and delete all products in storage by id
     * @param ids
     * @return
     */
    @Transactional
    public int deleteCategory(Long[] ids) {
        int count = 0;
        for(Long id : ids){
            CategoryTypeEntity storage = categoryRepository.findById(id).orElse(null);
            if (storage != null) {
                categoryRepository.delete(storage);
                count ++;
            }
            else{
                productRepository.deleteAllByStorageId(id);
                count ++;
            }
        }
        return count;
    }
}
