package org.wcci.checkers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.repositories.PieceRepository;
import java.util.Optional;

@RestController
@RequestMapping("/pieces")
public class PieceController {

    private PieceRepository pieceRepository;

    /**
     * Create/Update a piece
     * 
     * @param piece
     * @return
     */
    @PostMapping
    public PieceModel createPiece(@RequestBody PieceModel piece) {
        return pieceRepository.save(piece);
    }

    /**
     * retrieve a pieceModel
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<PieceModel> getPiece(@PathVariable long id) {
        Optional<PieceModel> piece = pieceRepository.findById(id);
        return piece.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * delete a piece
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePiece(@PathVariable long id) {
        if (!pieceRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // TODO is this the 404 response?
        }
        pieceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    // added 404 response

}