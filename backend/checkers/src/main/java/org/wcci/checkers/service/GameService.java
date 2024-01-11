package org.wcci.checkers.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.repositories.BoardRepository;
import org.wcci.checkers.repositories.PieceRepository;

@Service
public class GameService {

    private final BoardRepository boardRepository;
    private final PieceRepository pieceRepository;
    private final PieceService pieceService; 

    public GameService(BoardRepository boardRepository, PieceRepository pieceRepository, PieceService pieceService) {
        this.boardRepository = boardRepository;
        this.pieceRepository = pieceRepository;
        this.pieceService = pieceService;
    }

    @Transactional
    public BoardModel startGame() {
        BoardModel board = new BoardModel();
        board.drawTiles();
        // pieceService.setupPieces(board);
        board.drawPieces();
        return boardRepository.save(board);
    }

 
    public String checkGameState() {
        long blackPiecesCount = pieceRepository.countByColor("black");
        long redPiecesCount = pieceRepository.countByColor("red");

        boolean blackCanMove = canPlayerMove("black");
        boolean redCanMove = canPlayerMove("red");

        if (blackPiecesCount == 0 || !blackCanMove) {
            return "Red wins!";
        } else if (redPiecesCount == 0 || !redCanMove) {
            return "Black wins!";
        } else {
            return "Game is ongoing";
        }
    }

    private boolean canPlayerMove(String string) {
        return false;
    }
}