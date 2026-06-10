package com.hotel.backend.dto;

import com.hotel.backend.model.Product;
import com.hotel.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private int rating;
    private String comment;
    private LocalDate date;
    private Product product;
    private User user;
}