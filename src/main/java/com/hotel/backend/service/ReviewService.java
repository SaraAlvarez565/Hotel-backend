package com.hotel.backend.service;
import com.hotel.backend.exception.ResourceNotFoundException;
import com.hotel.backend.dto.ReviewRequest;
import com.hotel.backend.dto.ReviewResponse;
import com.hotel.backend.Mapper.ReviewMapper;
import com.hotel.backend.model.Product;
import com.hotel.backend.model.Review;
import com.hotel.backend.model.User;
import com.hotel.backend.repository.ProductRepository;
import com.hotel.backend.repository.ReviewRepository;
import com.hotel.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository repo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public ReviewService(
            ReviewRepository repo,
            ProductRepository productRepo,
            UserRepository userRepo
    ) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public ReviewResponse save(ReviewRequest request) {

        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Review review = ReviewMapper.toEntity(request);

        review.setProduct(product);
        review.setUser(user);
        review.setDate(LocalDate.now());

        Review saved = repo.save(review);

        return ReviewMapper.toResponse(saved);
    }

    public List<ReviewResponse> getByProduct(Long productId) {

        return repo.findByProductId(productId)
                .stream()
                .map(ReviewMapper::toResponse)
                .collect(Collectors.toList());
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