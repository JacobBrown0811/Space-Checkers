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
//         BoardModel board = gameService.startGame(board);
//         return ResponseEntity.ok(board);
//     }

//     @PutMapping("/move")
//     public ResponseEntity<?> movePiece(@RequestParam long pieceId, @RequestParam int newRow, @RequestParam int newColumn) {
//         boolean moveSuccessful = gameService.movePiece(pieceId, newRow, newColumn);
//         return moveSuccessful ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("Invalid move");
//     }

//     @PostMapping("/capture")
//     public ResponseEntity<?> capturePiece(@RequestParam long pieceId) {
//         boolean captureSuccessful = gameService.capturePiece(pieceId);
//         return captureSuccessful ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("Cant capture");
//     }

//     @GetMapping("/state")
//     public ResponseEntity<String> getGameState() {
//         String gameState = gameService.checkGameState();
//         return ResponseEntity.ok(gameState);
//     }
// }
