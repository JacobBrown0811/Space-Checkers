package org.wcci.checkers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.repositories.BoardRepository;
import org.wcci.checkers.repositories.PieceRepository;

import java.util.Optional;


@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardRepository boardRepository;

    private final PieceRepository pieceRepository;

    public BoardController(BoardRepository boardRepository, PieceRepository pieceRepository) {
        this.boardRepository = boardRepository;
        this.pieceRepository = pieceRepository;
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
    
    @GetMapping("/wipe/{id}")
    public ResponseEntity<BoardModel> resetBoard(@PathVariable long id) {
        Optional<BoardModel> board = boardRepository.findById(id);
        if (board.isPresent()) {
            // Delete the existing board
            boardRepository.deleteById(id);
            
            // Create a new board with the same ID
            BoardModel newBoard = new BoardModel();
            newBoard.setId(id);
            newBoard.drawTiles();
            newBoard.drawPieces();
            
            // Save the new board
            BoardModel savedBoard = boardRepository.save(newBoard);
            
            return ResponseEntity.ok(savedBoard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/clear")
    public ResponseEntity<?> clearPieces(@PathVariable long id) {
        Optional<BoardModel> board = boardRepository.findById(id);
        if (board.isPresent()) {
            // Explicitly load tiles if they are lazily loaded
            return ResponseEntity.ok(board.get().getPieces());
        } else {
            return ResponseEntity.notFound().build();
        }
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