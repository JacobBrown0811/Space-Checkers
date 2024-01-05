package org.wcci.checkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.repositories.PieceRepository;

@RestController
@RequestMapping("/pieces")
public class PieceController {

    @Autowired
    private PieceRepository pieceRepository;

    @PostMapping
    public PieceModel createPiece(@RequestBody PieceModel piece) {
        return pieceRepository.save(piece);
    }

    @GetMapping("/{id}")
    public PieceModel getPiece(@PathVariable long id) {
        return pieceRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletepiece(@PathVariable long id){
        if(!pieceRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().build();
    }
    


}