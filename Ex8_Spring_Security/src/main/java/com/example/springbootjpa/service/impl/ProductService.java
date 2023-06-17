package com.example.springbootjpa.service.impl;

import com.example.springbootjpa.dto.ProductDto;
import com.example.springbootjpa.entity.CategoryTypeEntity;
import com.example.springbootjpa.entity.ProductEntity;
import com.example.springbootjpa.entity.StorageEntity;
import com.example.springbootjpa.repository.CategoryTypeRepository;
import com.example.springbootjpa.repository.ProductRepository;
import com.example.springbootjpa.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final CategoryTypeRepository  categoryTypeRepository;
    private final StorageRepository storageRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapperProduct;

    /**
     * Save or update product
     * @param productDto
     * @return
     */
    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        // Nếu tìm thấy Id của product thì chức năng update sẽ được kích hoạt, nếu không thì chức năng create sẽ được kích hoạt
        if(productDto.getId() != null){
            productEntity = productRepository.findById(productDto.getId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDto.getId()));
            modelMapperProduct.map(productDto, productEntity);
        }else{
            productEntity = modelMapperProduct.map(productDto, ProductEntity.class);
        }

        CategoryTypeEntity categoryTypeEntity = categoryTypeRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDto.getCategory()));

        StorageEntity storageEntity = storageRepository.findById(productDto.getStorageId())
                .orElseThrow(() -> new RuntimeException("Storage not found with id: " + productDto.getStorageId()));

//        ProductEntity productEntity = productConverter.toEntity(productDto);
        productEntity.setCategories(categoryTypeEntity);
        productEntity.setStorages(storageEntity);
        productEntity = productRepository.save(productEntity);
        return modelMapperProduct.map(productEntity, ProductDto.class);
    }


    /**
     * Delete a list product by id
     * @param ids
     */
    public void delete(Long[] ids){
        for (Long id : ids){
            productRepository.deleteById(id);
        }
    }

    /**
     * Delete a product by id
     * @param id
     */
    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public ProductDto findById(Long id){
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return modelMapperProduct.map(productEntity, ProductDto.class);
    }

    /**
     * Lấy danh sách sản phẩm theo tên sản phẩm và tên danh mục, Nếu từ khóa tìm kiếm tên Danh mục = null
     * Chuyển qua tìm kiếm toàn bộ sản phầm theo từ khóa phần tên sản phẩm
     * @param page
     * @param size
     * @param productName
     * @param categoryName
     * @return
     */
    public Map<String, Object>  getProductByCategoryName(int page, int size, String productName,String categoryName) {
        Pageable paging = PageRequest.of(page, size, Sort.by(
                        Sort.Order.asc("id")
                )
        );
        Page<ProductEntity> pageProduct;
        List<ProductEntity> products = new ArrayList<ProductEntity>();
        if(categoryName.isEmpty()){
            pageProduct = productRepository.findAllByNameContainingIgnoreCase(productName, paging);
        }else{
            CategoryTypeEntity categoryTypeEntity = categoryTypeRepository.findByNameContainingIgnoreCase(categoryName).get();
            pageProduct = productRepository.findByNameContainingIgnoreCaseAndCategory(productName,categoryTypeEntity.getId(), paging);
        }
        products = pageProduct.getContent();

        // Mapping qua Dto
        Map<String, Object> response = new HashMap<>();
        List<ProductDto> productsDto = Arrays.asList(modelMapperProduct.map(products, ProductDto[].class));
        response.put("products", productsDto);
        response.put("currentPage", pageProduct.getNumber());
        response.put("totalItems", pageProduct.getTotalElements());
        response.put("totalPages", pageProduct.getTotalPages());
        log.info("Get product by category name success");
        return response;
    }

}
