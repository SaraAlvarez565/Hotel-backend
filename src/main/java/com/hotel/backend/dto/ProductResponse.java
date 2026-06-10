package com.hotel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;

    private Long categoryId;
    private String categoryName;

    private List<String> features;
}