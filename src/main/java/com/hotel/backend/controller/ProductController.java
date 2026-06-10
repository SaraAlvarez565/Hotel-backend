package com.hotel.backend.controller;

import com.hotel.backend.dto.ProductRequest;
import com.hotel.backend.dto.ProductResponse;
import com.hotel.backend.Mapper.ProductMapper;
import com.hotel.backend.model.Product;
import com.hotel.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return ProductMapper.toResponse(service.getById(id));
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {

        Product product = ProductMapper.toEntity(request);

        Product saved = service.save(product);

        return ProductMapper.toResponse(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}