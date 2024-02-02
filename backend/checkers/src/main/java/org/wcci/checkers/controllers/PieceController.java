package org.wcci.checkers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.repositories.PieceRepository;
import org.wcci.checkers.repositories.TileRepository;
import org.wcci.checkers.service.PieceService;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pieces")
public class PieceController {

    private final PieceRepository pieceRepository;
    private final TileRepository tileRepository;
    private final PieceService pieceService;

    public PieceController(PieceService pieceService, PieceRepository pieceRepository, TileRepository tileRepository) {
        this.pieceService = pieceService;
        this.pieceRepository = pieceRepository;
        this.tileRepository = tileRepository;
    }

    @PostMapping("/{id}")
    public ResponseEntity<PieceModel> createPiece(@RequestBody PieceModel piece) {
        PieceModel savedPiece = pieceRepository.save(piece);
        return ResponseEntity.ok(savedPiece);
    }


    @PutMapping("/{id}")
public ResponseEntity<?> putPiece(@PathVariable long id, @RequestBody Map<String, Object> moveDetails) {
    try {
        long newTileId = ((Number) moveDetails.get("newTileId")).longValue();
        Optional<Long> capturedPieceId = moveDetails.containsKey("capturedPieceId")
                                    ? Optional.of(((Number) moveDetails.get("capturedPieceId")).longValue())
                                    : Optional.empty();

        boolean moveSuccessful = pieceService.movePiece(id, newTileId, capturedPieceId);

        if (moveSuccessful) {
            return ResponseEntity.ok().body("Move or capture successful.");
        } else {
            return ResponseEntity.badRequest().body("Failed to move or capture the piece.");
        }
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}


    @GetMapping("/{id}")
    public ResponseEntity<PieceModel> getPiece(@PathVariable long id) {
        Optional<PieceModel> piece = pieceRepository.findById(id);
        return piece.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePiece(@PathVariable long id) {
        boolean exists = pieceRepository.existsById(id);
        if (!exists) {
            return ResponseEntity.notFound().build();
        }
        pieceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}