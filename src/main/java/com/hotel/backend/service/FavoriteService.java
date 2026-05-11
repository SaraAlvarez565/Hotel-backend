package com.hotel.backend.service;

import com.hotel.backend.model.Favorite;
import com.hotel.backend.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository repo;

    public FavoriteService(FavoriteRepository repo) {
        this.repo = repo;
    }

    public Favorite toggle(Favorite fav) {

        return repo.findByUserIdAndProductId(
                fav.getUser().getId(),
                fav.getProduct().getId()
        ).map(existing -> {
            repo.delete(existing);
            return existing;
        }).orElseGet(() -> repo.save(fav));
    }

    public List<Favorite> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }
}