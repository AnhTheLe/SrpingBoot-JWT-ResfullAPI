package com.example.springbootjpa.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BaseDto {
//    @NotNull(message = "Please enter id")
//    @Min(value = 1, message = "ID must be greater than or equal to 1")
    @Id
    private Long id;
    @NotEmpty(message = "Please enter product code")
    private String code;
    @NotEmpty(message = "Please enter product name")
    private String name;
    private Timestamp dateCreate;
    private Timestamp dateUpdate;
}
