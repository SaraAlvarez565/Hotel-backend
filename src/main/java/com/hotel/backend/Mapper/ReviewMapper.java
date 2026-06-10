package com.hotel.backend.Mapper;

import com.hotel.backend.dto.ReviewRequest;
import com.hotel.backend.dto.ReviewResponse;
import com.hotel.backend.model.Review;

public class ReviewMapper {

    public static Review toEntity(ReviewRequest r) {

        Review e = new Review();
        e.setRating(r.getRating());
        e.setComment(r.getComment());

        return e;
    }

    public static ReviewResponse toResponse(Review e) {

        return new ReviewResponse(
                e.getId(),
                e.getRating(),
                e.getComment(),
                e.getDate(),
                e.getProduct(),
                e.getUser()
        );
    }
}