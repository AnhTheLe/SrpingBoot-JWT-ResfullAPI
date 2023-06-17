package com.example.springbootjpa.dto;

import com.example.springbootjpa.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StorageDto extends BaseDto{
    private String address;

}
