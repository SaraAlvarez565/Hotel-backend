package com.hotel.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequest {

    @Min(1)
    @Max(5)
    private int rating;

    @NotBlank
    private String comment;

    @NotNull
    private Long productId;

    @NotNull
    private Long userId;
}