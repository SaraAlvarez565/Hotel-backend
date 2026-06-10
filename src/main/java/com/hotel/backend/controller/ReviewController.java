package com.hotel.backend.controller;

import com.hotel.backend.dto.ReviewRequest;
import com.hotel.backend.dto.ReviewResponse;
import com.hotel.backend.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ReviewResponse create(@Valid @RequestBody ReviewRequest request) {
        return service.save(request);
    }

    @GetMapping("/product/{id}")
    public List<ReviewResponse> getByProduct(@PathVariable Long id) {
        return service.getByProduct(id);
    }

    @GetMapping("/average/{id}")
    public double average(@PathVariable Long id) {
        return service.getAverage(id);
    }
}