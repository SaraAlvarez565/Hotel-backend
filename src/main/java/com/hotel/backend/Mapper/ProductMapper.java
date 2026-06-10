package com.hotel.backend.Mapper;

import com.hotel.backend.dto.ProductRequest;
import com.hotel.backend.dto.ProductResponse;
import com.hotel.backend.model.Category;
import com.hotel.backend.model.Product;

import java.util.ArrayList;

public class ProductMapper {

    public static Product toEntity(ProductRequest dto) {

        Product p = new Product();

        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setImageUrl(dto.getImageUrl());


        if (dto.getCategoryId() != null) {
            Category c = new Category();
            c.setId(dto.getCategoryId());
            p.setCategory(c);
        }

        p.setFeatures(new ArrayList<>());

        return p;
    }

    public static ProductResponse toResponse(Product p) {

        ProductResponse dto = new ProductResponse ();

        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setImageUrl(p.getImageUrl());

        if (p.getCategory() != null) {
            dto.setCategoryId(p.getCategory().getId());
            dto.setCategoryName(p.getCategory().getName());
        }

        if (p.getFeatures() != null) {
            dto.setFeatures(
                    p.getFeatures()
                            .stream()
                            .map(f -> f.getName())
                            .toList()
            );
        }

        return dto;
    }
}