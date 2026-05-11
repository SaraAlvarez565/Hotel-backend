package com.hotel.backend.service;

import com.hotel.backend.model.Review;
import com.hotel.backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repo;

    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public Review save(Review r) {
        r.setDate(LocalDate.now());
        return repo.save(r);
    }

    public List<Review> getByProduct(Long productId) {
        return repo.findByProductId(productId);
    }

    public double getAverage(Long productId) {
        List<Review> list = repo.findByProductId(productId);

        if (list.isEmpty()) return 0;

        return list.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
    }
}