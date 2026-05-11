package com.hotel.backend.controller;

import com.hotel.backend.model.Reservation;
import com.hotel.backend.service.EmailService;
import com.hotel.backend.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin("*")
public class ReservationController {

    private final ReservationService service;
    private final EmailService emailService;

    public ReservationController(ReservationService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation r) {

        Reservation saved = service.save(r);

        emailService.sendReservationEmail(
                "usuario@test.com",
                saved.getProduct().getName(),
                saved.getStartDate().toString(),
                saved.getEndDate().toString()
        );

        return saved;
    }

    @GetMapping("/product/{id}")
    public List<Reservation> getByProduct(@PathVariable Long id) {
        return service.getByProduct(id);
    }

    @GetMapping("/user/{id}")
    public List<Reservation> getByUser(@PathVariable Long id) {
        return service.getByUser(id);
    }
}