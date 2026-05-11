package com.hotel.backend.controller;

import com.hotel.backend.model.Favorite;
import com.hotel.backend.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin("*")
public class FavoriteController {

    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @PostMapping
    public Favorite toggle(@RequestBody Favorite fav) {
        return service.toggle(fav);
    }

    @GetMapping("/user/{id}")
    public List<Favorite> getByUser(@PathVariable Long id) {
        return service.getByUser(id);
    }
}