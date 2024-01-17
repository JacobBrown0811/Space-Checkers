package org.wcci.checkers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.repositories.PieceRepository;
import org.wcci.checkers.repositories.TileRepository;
import org.wcci.checkers.service.PieceService;

import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Array;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pieces")
public class PieceController {

    private PieceRepository pieceRepository;
    private TileRepository tileRepository;
    private final PieceService pieceService;

    public PieceController (PieceService pieceService){
        this.pieceService = pieceService;
    }
    /**
     * Create/Update a piece
     * 
     * @param piece
     * @return
     */
    @PostMapping("/{id}")
    public PieceModel createPiece(@RequestBody PieceModel piece) {
        return pieceRepository.save(piece);
    }


    // TODO can't have more than one @mapping of each type
    // @PutMapping("/{id}")
    // public ResponseEntity<?> putPiece(@PathVariable long id, HttpServletRequest request) {
    //     try {
    //         PieceModel model = new PieceModel();
    //         model.setTile(tileRepository.findById(id).get());
    //         pieceRepository.save(model);
    //         return ResponseEntity.ok("");
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         return ResponseEntity.ok("bad");
    //     }
        
    // }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPiece(@RequestBody int[] both) { // needs requestBody to pass args
        try {
            long piece = both[0];
            long tile = both[1];
            System.out.println("numbers: " + piece +", " + tile);
            // PieceService pieceService = new PieceService(pieceRepository, tileRepository);
            pieceService.movePiece(both[0], both[1]);
            System.out.println("PutMapping movePiece successful");
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Putmapping movePiece failed" + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
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