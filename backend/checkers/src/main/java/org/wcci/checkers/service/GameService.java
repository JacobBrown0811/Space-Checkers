package org.wcci.checkers.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.models.MoveModel;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.repositories.BoardRepository;
import org.wcci.checkers.repositories.PieceRepository;

import java.util.Optional;

@Service
public class GameService {

    private final BoardRepository boardRepository;
    private final PieceRepository pieceRepository;
    private final PieceService pieceService;
    private final AIService aiService; // AIService for AI integration
    private String currentPlayer; // Keep track of the current player

    public GameService(BoardRepository boardRepository, PieceRepository pieceRepository, PieceService pieceService, AIService aiService) {
        this.boardRepository = boardRepository;
        this.pieceRepository = pieceRepository;
        this.pieceService = pieceService;
        this.aiService = aiService; // Initialize AIService
        this.currentPlayer = "blue"; // Assuming blue starts, adjust as needed
    }

    @Transactional
    public BoardModel startGame() {
        BoardModel board = new BoardModel();
        board.drawTiles();
        board.drawPieces();
        return boardRepository.save(board);
    }

    public String checkGameState() {
        long bluePiecesCount = pieceRepository.countByColor("blue");
        long redPiecesCount = pieceRepository.countByColor("red");

        boolean blueCanMove = canPlayerMove("blue");
        boolean redCanMove = canPlayerMove("red");

        if (bluePiecesCount == 0 || !blueCanMove) {
            return "Red wins!";
        } else if (redPiecesCount == 0 || !redCanMove) {
            return "Blue wins!";
        } else {
            return "Game is ongoing";
        }
    }

    private boolean canPlayerMove(String color) {
        Iterable<PieceModel> pieces = pieceRepository.findByColor(color);
        for (PieceModel piece : pieces) {
            if (pieceService.canMove(piece.getId()) || pieceService.canCapture(piece.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean makeMove(MoveModel move, String playerColor) {
        if (!playerColor.equals(currentPlayer)) {
            // It's not this player's turn
            return false;
        }

        // Logic to apply the move (you'll need to implement this)
        boolean moveApplied = applyMove(move);
        if (moveApplied) {
            // Switch the current player
            currentPlayer = currentPlayer.equals("blue") ? "red" : "blue";
            // If the current player is AI, trigger AI move
            if (currentPlayer.equals("red")) {
                makeAIMove(move.getBoardId()); // Assuming MoveModel has getBoardId()
            }
        }
        return moveApplied;
    }

    public MoveModel makeAIMove(Long boardId) {
        Optional<BoardModel> boardOpt = boardRepository.findById(boardId);
        if (boardOpt.isPresent()) {
            BoardModel board = boardOpt.get();
            return aiService.calculateBestMove(board);
        }
        return null;
    }

    // Implement this method based on how a move should be applied in your game
    private boolean applyMove(MoveModel move) {
        // Apply the move to the board
        // Validate the move
        // Update the board and pieces
        // Return true if the move was applied successfully
        return false;
    }
}