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

    /**
     * returns new board
     * 
     * @param board
     * @return
     */
    @PostMapping
    public BoardModel createBoard(@RequestBody BoardModel board) {
        return boardRepository.save(board);
    }

    /**
     * return tiles if the board exists
     * 
     * @param id
     * @return
     */
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

    /**
     * return tiles by board ID
     * 
     * @param id
     * @return
     */
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

    /**
     * return pieces byt board ID
     * 
     * @param id
     * @return
     */
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

    /**
     * Update board by ID
     * 
     * @param id
     * @param updatedBoard
     * @return
     */
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

    /**
     * Delete board by ID
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable long id) {
        if (!boardRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        boardRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Return all boards
     * 
     * @return
     */
    @GetMapping("")
    public Iterable<BoardModel> getAllBoards() {
        return boardRepository.findAll();
    }
}