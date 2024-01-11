package org.wcci.checkers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.repositories.BoardRepository;

import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @PostMapping
    public BoardModel createBoard(@RequestBody BoardModel board) {
        return boardRepository.save(board);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardModel> getBoard(@PathVariable long id) {
        Optional<BoardModel> board = boardRepository.findById(id);
        if (board.isPresent()) {
            // Explicitly load tiles if they are lazily loaded
            board.get().getTiles().size(); // This line is required if tiles are lazily loaded
            return ResponseEntity.ok(board.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/tiles")
    public ResponseEntity<?> getBoardTiles(@PathVariable long id) {
        Optional<BoardModel> board = boardRepository.findById(id);
        if (board.isPresent()) {
            // Explicitly load tiles if they are lazily loaded
            return ResponseEntity.ok(board.get().getTiles());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pieces")
    public ResponseEntity<?> getBoardPieces(@PathVariable long id) {
        Optional<BoardModel> board = boardRepository.findById(id);
        if (board.isPresent()) {
            // Explicitly load tiles if they are lazily loaded
            return ResponseEntity.ok(board.get().getPieces());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<BoardModel> updateBoard(@PathVariable long id, @RequestBody BoardModel updatedBoard) {
        return boardRepository.findById(id)
            .map(board -> {
                // Update the properties of board with updatedBoard
                // For example: board.setSomeProperty(updatedBoard.getSomeProperty());
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

    @GetMapping("")
    public Iterable<BoardModel> getAllBoards() {
        return boardRepository.findAll();
    }
}