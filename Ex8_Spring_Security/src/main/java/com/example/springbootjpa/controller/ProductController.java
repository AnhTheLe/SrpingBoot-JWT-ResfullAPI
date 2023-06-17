package com.example.springbootjpa.controller;

import com.example.springbootjpa.dto.ProductDto;
import com.example.springbootjpa.entity.ResponseObject;
import com.example.springbootjpa.service.impl.ProductService;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class ProductController {

    private final ProductService productService;
    @PostMapping("/products")
    public ResponseEntity<ResponseObject> save( @Valid @RequestBody ProductDto product) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "Insert dữ liệu thành công", product));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable("id") Long id,@Valid @RequestBody ProductDto product) {
        product.setId(id);
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "Cập nhật dữ liệu thành công", ""));
    }

    @DeleteMapping("/products")
    public ResponseEntity<ResponseObject> delete(@RequestBody Long[] ids) {
        productService.delete(ids);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "Xóa dữ liệu thành công", ""));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable("id") Long id) {
        ProductDto product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "", product));
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseObject> getAllProducts(
            @RequestParam(defaultValue = "", name = "keyword") String keyword,
            @RequestParam(defaultValue = "", name = "categoryName") String categoryName,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        Map<String, Object> response = productService.getProductByCategoryName(page, size, keyword, categoryName);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "", response));
    }

}
