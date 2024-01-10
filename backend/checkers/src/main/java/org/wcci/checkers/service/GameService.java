// package org.wcci.checkers.service;

// import org.wcci.checkers.models.BoardModel;
// import org.wcci.checkers.repositories.BoardRepository;
// import org.wcci.checkers.repositories.PieceRepository;
// import org.wcci.checkers.repositories.PlayerRepository;

// public class GameService {
//     private final BoardRepository boardRepository;
//     private final PieceRepository pieceRepository;
//     private final PlayerRepository playerRepository;

//     public GameService(BoardRepository boardRepository, PieceRepository pieceRepository,
//             PlayerRepository playerRepository) {
//         this.boardRepository = boardRepository;
//         this.pieceRepository = pieceRepository;
//         this.playerRepository = playerRepository;
//     }

//     public BoardModel startGame() {
//         BoardModel board = new BoardModel();

//         // Setting up black pieces in the first three rows
//         for (int row = 0; row < 3; row++) {
//             for (int col = (row % 2 == 0) ? 1 : 0; col < 8; col += 2) {
//                 Piece blackPiece = new Piece(Color.BLACK);
//                 board.placePiece(blackPiece, row, col);
//             }
//         }

//         // Setting up red pieces in the last three rows
//         for (int row = 5; row < 8; row++) {
//             for (int col = (row % 2 == 0) ? 1 : 0; col < 8; col += 2) {
//                 Piece redPiece = new Piece(Color.RED);
//                 board.placePiece(redPiece, row, col);
//             }
//         }

//         return board;
//     }
// }
