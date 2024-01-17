package org.wcci.checkers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.PlayerModel;
import org.wcci.checkers.repositories.PlayerRepository;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerRepository playerRepository;

    /**
     * Creates player
     * 
     * @param player
     * @return
     */
    @PostMapping
    public PlayerModel createPlayer(@RequestBody PlayerModel player) {
        return playerRepository.save(player);
    }

    @GetMapping("/{id}")
    public PlayerModel getPlayer(@PathVariable long id) {
        return playerRepository.findById(id).orElse(null);
    }

    /**
     * This finds the player by ID & updates its property/ if not found 404
     * 
     * @param id
     * @param updatedPlayer
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlayerModel> updatePlayer(@PathVariable long id, @RequestBody PlayerModel updatedPlayer) {
        return playerRepository.findById(id)
                .map(player -> {
                    player.setColor(updatedPlayer.getColor());
                    // Add other fields to update as needed
                    PlayerModel savedPlayer = playerRepository.save(player);
                    return ResponseEntity.ok(savedPlayer);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * checks if player ID exists if it do the player gets deleted. Ok response is
     * returned & if not 404 is returned
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}