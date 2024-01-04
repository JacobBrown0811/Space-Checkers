package org.wcci.checkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.TileModel;
import org.wcci.checkers.repositories.TileRepository;

@RestController
@RequestMapping("/tiles")
public class TileController {

    @Autowired
    private TileRepository tileRepository;

    @PostMapping
    public TileModel createTile(@RequestBody TileModel tile) {
        return tileRepository.save(tile);
    }

    @GetMapping("/{id}")
    public TileModel getTile(@PathVariable long id) {
        return tileRepository.findById(id).orElse(null);
    }


}
