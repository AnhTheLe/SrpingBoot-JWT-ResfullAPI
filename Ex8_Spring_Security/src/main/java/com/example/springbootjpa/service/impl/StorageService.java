package com.example.springbootjpa.service.impl;

import com.example.springbootjpa.dto.StorageDto;
import com.example.springbootjpa.entity.StorageEntity;
import com.example.springbootjpa.repository.ProductRepository;
import com.example.springbootjpa.repository.StorageRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
public class StorageService {
    private final ProductRepository productRepository;
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapperStorage;

    /**
     * Save or update storage
     * @param storageDto
     * @return
     */
    public StorageDto save(StorageDto storageDto) {
        StorageEntity storageEntity = new StorageEntity();
        if(storageDto.getId() != null ){
            storageEntity = storageRepository.findById(storageDto.getId())
                    .orElseThrow(() -> new RuntimeException("Storage not found with id: " + storageDto.getId()));
            modelMapperStorage.map(storageDto, storageEntity);
        }else{
            storageEntity = modelMapperStorage.map(storageDto, StorageEntity.class);
        }
        storageEntity = storageRepository.save(storageEntity);
        return modelMapperStorage.map(storageEntity, StorageDto.class);
    }

    /**
     * find a storage by id
     * @param id
     * @return
     */
    public StorageDto findById(Long id) {
        StorageEntity storageEntity = storageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Storage not found with id: " + id));
        return modelMapperStorage.map(storageEntity, StorageDto.class);
    }

    /**
     * Find all storage
     * @return
     */
    public Map<String, Object> getAllStorage(int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by(
                        Sort.Order.asc("id")
                )
        );
        Page<StorageEntity> pageStorage;
        List<StorageEntity> storages = new ArrayList<StorageEntity>();
        pageStorage = storageRepository.findAll(paging);
        storages = pageStorage.getContent();
        // Mapping qua Dto
        Map<String, Object> response = new HashMap<>();
        List<StorageDto> storageDto = Arrays.asList(modelMapperStorage.map(storages, StorageDto[].class));
        response.put("products", storageDto);
        response.put("currentPage", pageStorage.getNumber());
        response.put("totalItems", pageStorage.getTotalElements());
        response.put("totalPages", pageStorage.getTotalPages());
        return response;
    }

    /**
     * Delete a list storages by list id, and delete all products in storage by id
     * @param ids
     * @return
     */
    @Transactional
    public int deleteStorage(Long[] ids) {
        int count = 0;
        for(Long id : ids){
            StorageEntity storage = storageRepository.findById(id).orElse(null);
            if (storage != null) {
                storageRepository.delete(storage);
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
