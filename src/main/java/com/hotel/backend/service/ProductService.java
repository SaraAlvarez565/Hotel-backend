package com.hotel.backend.service;

import com.hotel.backend.model.Product;
import com.hotel.backend.repository.FavoriteRepository;
import com.hotel.backend.repository.ProductRepository;
import com.hotel.backend.repository.ReservationRepository;
import com.hotel.backend.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;

    public ProductService(
            ProductRepository repository,
            ReservationRepository reservationRepository,
            ReviewRepository reviewRepository,
            FavoriteRepository favoriteRepository
    ) {
        this.repository = repository;
        this.reservationRepository = reservationRepository;
        this.reviewRepository = reviewRepository;
        this.favoriteRepository = favoriteRepository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
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

    public void delete(Long id) {

        favoriteRepository.deleteByProductId(id);

        reviewRepository.deleteByProductId(id);

        reservationRepository.deleteByProductId(id);

        repository.deleteById(id);
    }
}