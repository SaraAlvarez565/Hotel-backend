package com.hotel.backend.controller;

import com.hotel.backend.model.Review;
import com.hotel.backend.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public Review create(@RequestBody Review r) {
        return service.save(r);
    }

    @GetMapping("/product/{id}")
    public List<Review> getByProduct(@PathVariable Long id) {
        return service.getByProduct(id);
    }

    @GetMapping("/average/{id}")
    public double average(@PathVariable Long id) {
        return service.getAverage(id);
    }
}
