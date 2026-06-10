package com.hotel.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha inicial es obligatoria")
    @FutureOrPresent(message = "La fecha inicial debe ser hoy o futura")
    @NotNull
    private LocalDate startDate;

    @NotNull(message = "La fecha final es obligatoria")
    @FutureOrPresent(message = "La fecha final debe ser hoy o futura")
    @NotNull
    private LocalDate endDate;

    @ManyToOne
    @JsonIgnoreProperties({
            "features",
            "category"
    })
    private Product product;

    @ManyToOne
    private User user;
}