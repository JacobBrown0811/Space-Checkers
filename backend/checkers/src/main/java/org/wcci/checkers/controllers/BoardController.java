package org.wcci.checkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.repositories.BoardRepository;

import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;


    @PostMapping
    public BoardModel createBoard(@RequestBody BoardModel board) {
        return boardRepository.save(board);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BoardModel> getBoard(@PathVariable long id) {
        Optional<BoardModel> board = boardRepository.findById(id);
        if (!board.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(board.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardModel> updateBoard(@PathVariable long id, @RequestBody BoardModel updatedBoard) {
        return boardRepository.findById(id)
            .map(board -> {
                BoardModel savedBoard = boardRepository.save(board);
                return ResponseEntity.ok(savedBoard);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable long id) {
        if (!boardRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        boardRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Iterable<BoardModel> getAllBoards() {
        return boardRepository.findAll();
    }
}