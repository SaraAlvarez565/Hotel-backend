package com.hotel.backend.service;
import com.hotel.backend.exception.ResourceNotFoundException;
import com.hotel.backend.dto.ReservationRequest;
import com.hotel.backend.dto.ReservationResponse;
import com.hotel.backend.Mapper.ReservationMapper;
import com.hotel.backend.model.Product;
import com.hotel.backend.model.Reservation;
import com.hotel.backend.model.User;
import com.hotel.backend.repository.ProductRepository;
import com.hotel.backend.repository.ReservationRepository;
import com.hotel.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository repo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public ReservationService(
            ReservationRepository repo,
            ProductRepository productRepo,
            UserRepository userRepo
    ) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public ReservationResponse save(ReservationRequest request) {

        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Reservation reservation = ReservationMapper.toEntity(request);

        reservation.setProduct(product);
        reservation.setUser(user);

        Reservation saved = repo.save(reservation);

        return ReservationMapper.toResponse(saved);
    }

    public List<ReservationResponse> getByProduct(Long productId) {

        return repo.findByProductId(productId)
                .stream()
                .map(ReservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ReservationResponse> getByUser(Long userId) {

        return repo.findByUserId(userId)
                .stream()
                .map(ReservationMapper::toResponse)
                .collect(Collectors.toList());
    }
}