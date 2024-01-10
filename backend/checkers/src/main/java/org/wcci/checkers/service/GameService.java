// package org.wcci.checkers.service;

// import org.springframework.stereotype.Service;
// import org.wcci.checkers.models.BoardModel;
// import org.wcci.checkers.repositories.BoardRepository;
// import org.wcci.checkers.repositories.PieceRepository;

// @Service
// public class GameService {

//     private final BoardRepository boardRepository;
//     private final PieceRepository pieceRepository;

//     public GameService(BoardRepository boardRepository, PieceRepository pieceRepository) {
//         this.boardRepository = boardRepository;
//         this.pieceRepository = pieceRepository;
//     }

//     public BoardModel startGame() {
//         BoardModel board = new BoardModel();
//         board.drawBoard();
//         setupPieces(board);
//         return boardRepository.save(board);
//     }

//     public String checkGameState() {
//         long blackPiecesCount = pieceRepository.countByColor("black");
//         long redPiecesCount = pieceRepository.countByColor("red");

//         boolean blackCanMove = canPlayerMove("black");
//         boolean redCanMove = canPlayerMove("red");

//         if (blackPiecesCount == 0 || !blackCanMove) {
//             return "Red wins!";
//         } else if (redPiecesCount == 0 || !redCanMove) {
//             return "Black wins!";
//         } else {
//             return "Game is ongoing";
//         }
//     }
// }