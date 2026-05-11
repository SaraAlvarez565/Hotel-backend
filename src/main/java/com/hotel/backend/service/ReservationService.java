package com.hotel.backend.service;

import com.hotel.backend.model.Reservation;
import com.hotel.backend.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repo;

    public ReservationService(ReservationRepository repo) {
        this.repo = repo;
    }

    public Reservation save(Reservation r) {
        return repo.save(r);
    }

    public List<Reservation> getByProduct(Long productId) {
        return repo.findByProductId(productId);
    }

    public List<Reservation> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }
}