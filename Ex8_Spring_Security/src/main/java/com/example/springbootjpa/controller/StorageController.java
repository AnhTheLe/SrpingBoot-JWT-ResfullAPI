package com.example.springbootjpa.controller;

import com.example.springbootjpa.dto.StorageDto;
import com.example.springbootjpa.entity.ResponseObject;
import com.example.springbootjpa.entity.StorageEntity;
import com.example.springbootjpa.service.impl.StorageService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/v1/admin")
public class StorageController {
    @Autowired
    private StorageService storageService;
    @PostMapping("/storages")
    public ResponseEntity<ResponseObject> save(@RequestBody StorageDto storage) {
        storageService.save(storage);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "", storage));
    }

    @PutMapping("/storages/{id}")
    public ResponseEntity<ResponseObject> update(@Valid  @RequestBody StorageDto storage, @PathVariable Long id){
        storage.setId(id);
        storageService.save(storage);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "Cập nhật dữ liệu thành công", ""));
    }

    @DeleteMapping("/storages")
    public ResponseEntity<ResponseObject> delete(@RequestBody Long[] ids){
        int countDeleted = storageService.deleteStorage(ids);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "Xóa dữ liệu thành công", countDeleted));
    }

    @GetMapping("/storages")
    public ResponseEntity<ResponseObject> getAllProducts(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size

    ) {
        Map<String, Object> response = storageService.getAllStorage(page, size);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("success", "", response));
    }
}
