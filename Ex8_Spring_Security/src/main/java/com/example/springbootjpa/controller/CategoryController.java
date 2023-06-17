package com.example.springbootjpa.controller;

import com.example.springbootjpa.dto.CategoryDto;
import com.example.springbootjpa.entity.ResponseObject;
import com.example.springbootjpa.service.impl.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping("/categories")
    public ResponseEntity<ResponseObject> save(@RequestBody CategoryDto category) {
        categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "", category));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<ResponseObject> update(@Valid @RequestBody CategoryDto category, @PathVariable Long id){
        category.setId(id);
        categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "Cập nhật dữ liệu thành công", ""));
    }

    @DeleteMapping("/categories")
    public ResponseEntity<ResponseObject> delete(@RequestBody Long[] ids){
        int countDeleted = categoryService.deleteCategory(ids);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "D", countDeleted));
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseObject> getAllProducts(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        Map<String, Object> response = categoryService.getAllCategories(page, size);
        log.info("User registered successfully: {}", response.get("data"));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "", response));
    }
}
