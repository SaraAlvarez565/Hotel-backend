package com.hotel.backend.controller;

import com.hotel.backend.dto.ReservationRequest;
import com.hotel.backend.dto.ReservationResponse;
import com.hotel.backend.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ReservationResponse create(@Valid @RequestBody ReservationRequest request) {
        return service.save(request);
    }

    @GetMapping("/product/{id}")
    public List<ReservationResponse> getByProduct(@PathVariable Long id) {
        return service.getByProduct(id);
    }

    @GetMapping("/user/{id}")
    public List<ReservationResponse> getByUser(@PathVariable Long id) {
        return service.getByUser(id);
    }
}