package com.hotel.backend.dto;

import com.hotel.backend.model.Product;
import com.hotel.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReservationResponse {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Product product;
    private User user;
}