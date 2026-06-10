package com.hotel.backend.Mapper;

import com.hotel.backend.dto.ReservationRequest;
import com.hotel.backend.dto.ReservationResponse;
import com.hotel.backend.model.Reservation;

public class ReservationMapper {

    public static Reservation toEntity(ReservationRequest r) {

        Reservation e = new Reservation();

        e.setStartDate(r.getStartDate());
        e.setEndDate(r.getEndDate());

        // relaciones se asignan en service
        return e;
    }

    public static ReservationResponse toResponse(Reservation e) {

        return new ReservationResponse(
                e.getId(),
                e.getStartDate(),
                e.getEndDate(),
                e.getProduct(),
                e.getUser()
        );
    }
}