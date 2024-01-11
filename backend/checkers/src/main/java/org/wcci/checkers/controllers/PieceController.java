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

    @PostMapping
    public PieceModel createPiece(@RequestBody PieceModel piece) {
        return pieceRepository.save(piece);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceModel> getPiece(@PathVariable long id) {
        Optional<PieceModel> piece = pieceRepository.findById(id);
        return piece.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePiece(@PathVariable long id) {
        if (!pieceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pieceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    // added 404 response 


}