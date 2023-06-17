package com.example.springbootjpa.config;

import com.example.springbootjpa.dto.CategoryDto;
import com.example.springbootjpa.dto.ProductDto;
import com.example.springbootjpa.dto.StorageDto;
import com.example.springbootjpa.entity.CategoryTypeEntity;
import com.example.springbootjpa.entity.ProductEntity;
import com.example.springbootjpa.entity.StorageEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    public void ModelMapperConfig(){};
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
        modelMapper.createTypeMap(ProductDto.class, ProductEntity.class)
                .addMappings(mapper -> mapper.skip(ProductEntity::setDateCreate));
        modelMapper.createTypeMap(StorageDto.class, StorageEntity.class)
                .addMappings(mapper -> mapper.skip(StorageEntity::setDateCreate));
        modelMapper.createTypeMap(CategoryDto.class, CategoryTypeEntity.class)
                .addMappings(mapper -> mapper.skip(CategoryTypeEntity::setDateCreate));
        return modelMapper;
    }
}
