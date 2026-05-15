package com.hotel.backend.repository;

import com.hotel.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByProductId(Long productId);

    List<Reservation> findByUserId(Long userId);

    void deleteByProductId(Long id);
}