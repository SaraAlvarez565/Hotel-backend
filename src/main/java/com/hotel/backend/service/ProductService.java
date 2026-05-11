package com.hotel.backend.service;

import com.hotel.backend.model.Product;
import com.hotel.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public List<Product> search(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> byCategory(Long id) {
        return repository.findByCategoryId(id);
    }

    public Product save(Product p) {
        return repository.save(p);
    }
}