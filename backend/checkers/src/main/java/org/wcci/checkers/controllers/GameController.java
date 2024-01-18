package org.wcci.checkers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.models.MoveModel;
import org.wcci.checkers.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Starts new game and establishes board
     * 
     * @return
     */
    @PostMapping("/start")
    public ResponseEntity<BoardModel> startGame() {
        BoardModel board = gameService.startGame();
        return ResponseEntity.ok(board);
    }

    /**
     * Returns game state
     * 
     * @return
     */
    @GetMapping("/state")
    public ResponseEntity<String> checkGameState() {
        String gameState = gameService.checkGameState();
        return ResponseEntity.ok(gameState);
    }
     /**
     * Handles making a move
     * 
     * @param move
     * @return
     */
    @PostMapping("/move")
    public ResponseEntity<?> makeMove(@RequestBody MoveModel move) {
        boolean success = gameService.makeMove(move, "player"); // You might want to adjust how you determine the player
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Invalid move or not your turn.");
        }
    }

    /**
     * Fetches the AI's move
     * hg
     *need to get the board id here maybe from the session or a request parameter
     * @return
     */
    @GetMapping("/ai/move")
public ResponseEntity<MoveModel> getAIMove(@RequestParam Long boardId) {
    MoveModel move = gameService.makeAIMove(boardId);
    if (move != null) {
        return ResponseEntity.ok(move);
    } else {
        return ResponseEntity.badRequest().body(new MoveModel(boardId, 0, 0, 0, 0)); // Provide a default MoveModel or handle the case when move is null
    }
}
}