package com.example.springbootjpa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

// Dữ liệu trả về DTO
@Getter
@Setter
@AllArgsConstructor
public class ProductDto extends BaseDto{
    @NotNull(message = "Please enter category id")
    @Min(value = 1, message = "Category ID must be greater than or equal to 1")
    private Long category;
    @NotNull(message = "Please enter cost")
    private Long cost;
    @NotNull(message = "Please enter storage id")
    @Min(value = 1, message = "Category ID must be greater than or equal to 1")
    private Long storageId;

    private String intro;
//    @NotEmpty(message = "Please enter image link")
    private String imageLink;
    @NotNull(message = "Please enter quantity")
    private Long quantity;
    @NotNull(message = "Please enter sell quantity")
    private Long sellQuantity;


    public ProductDto() {

    }


    public ProductDto(String success, String s, String code) {
        super();
    }
}
