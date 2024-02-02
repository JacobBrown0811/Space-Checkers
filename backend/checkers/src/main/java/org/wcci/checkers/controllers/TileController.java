package org.wcci.checkers.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.TileModel;

import org.wcci.checkers.repositories.TileRepository;
import java.util.Optional;


@RestController
@RequestMapping("/tiles")
public class TileController {

    
    private final TileRepository tileRepository;

    public TileController(TileRepository tileRepository) {
        this.tileRepository = tileRepository;
    }

  

    @PostMapping
    public TileModel createTile(@RequestBody TileModel tile) {
        return tileRepository.save(tile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TileModel> getTile(@PathVariable long id) {
        Optional<TileModel> tile = tileRepository.findById(id);
        return tile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
//     @PutMapping("/{id}")
//     public ResponseEntity<TileModel> updateTile(@PathVariable long id, @RequestBody TileModel updatedTile) {
//         return tileRepository.findById(id)
//         .map(tile -> {
//             TileModel savedTile = tileRepository.save(tile);
//             return ResponseEntity.ok(savedTile);
//         }).orElseGet(() -> ResponseEntity.notFound().build());
        

// }


// updates the tiles if it becomes occupied or unoccupied
@PutMapping("/{id}")
    public ResponseEntity<TileModel> updateTile(@PathVariable long id, @RequestBody TileModel updatedTile) {
        return tileRepository.findById(id)
            .map(tile -> {
                tile.setIsOccupied(updatedTile.getIsOccupied());
                tile.setBoardColumn(updatedTile.getBoardColumn());
                tile.setBoardRow(updatedTile.getBoardRow());
                tile.setColor(updatedTile.getColor());
                tile.setIsPlayable(updatedTile.getIsPlayable());
                TileModel savedTile = tileRepository.save(tile);
                return ResponseEntity.ok(savedTile);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
