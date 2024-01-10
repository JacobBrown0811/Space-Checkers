// package org.wcci.checkers.controllers;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.wcci.checkers.models.BoardModel;
// import org.wcci.checkers.service.GameService;

// @RestController
// @RequestMapping("/game")
// public class GameController {

//     private final GameService gameService;

//     public GameController(GameService gameService) {
//         this.gameService = gameService;
//     }

//     @PostMapping("/start")
//     public ResponseEntity<BoardModel> startGame() {
//         BoardModel board = gameService.startGame();
//         return ResponseEntity.ok(board);
//     }

//     @GetMapping("/state")
//     public ResponseEntity<String> checkGameState() {
//         String gameState = gameService.checkGameState();
//         return ResponseEntity.ok(gameState);
//     }
// }