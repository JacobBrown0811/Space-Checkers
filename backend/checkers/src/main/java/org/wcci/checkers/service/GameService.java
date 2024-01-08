package org.wcci.checkers.service;



import org.springframework.stereotype.Service;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.models.TileModel;
import org.wcci.checkers.repositories.BoardRepository;
import org.wcci.checkers.repositories.PieceRepository;
import org.wcci.checkers.repositories.TileRepository;

import java.util.Optional;

@Service
public class GameService {

    private final BoardRepository boardRepository;
    private final PieceRepository pieceRepository;
    private final TileRepository tileRepository;

    
    public GameService(BoardRepository boardRepository, PieceRepository pieceRepository, TileRepository tileRepository) {
        this.boardRepository = boardRepository;
        this.pieceRepository = pieceRepository;
        this.tileRepository = tileRepository;
    }

    public BoardModel startGame(BoardModel board) {
        board.drawBoard();
        setupPieces(board);
        return boardRepository.save(board);
    }
    public boolean movePiece(long pieceId, int newRow, int newColumn) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (pieceOpt.isPresent()) {
            PieceModel piece = pieceOpt.get();
            // Add more validation for move legality according to checkers rules
            piece.setBoardRow(newRow);
            piece.setBoardColumn(newColumn);
            pieceRepository.save(piece);
            return true;
        }
        return false;
    }
    public String checkGameState() {
        // Placeholder logic for checking the game state
        long blackPiecesCount = pieceRepository.countByColor("black");
        long redPiecesCount = pieceRepository.countByColor("red");

        // Check if either player has no pieces left
        if (blackPiecesCount == 0) return "Red wins!";
        if (redPiecesCount == 0) return "Black wins!";

        // Check for available moves for each player
        boolean blackCanMove = pieceRepository.existsByColorAndCanMove("black", true);
        boolean redCanMove = pieceRepository.existsByColorAndCanMove("red", true);

        // Determine the game state based on available moves
        if (!blackCanMove && !redCanMove) {
            return "Draw!";
        } else if (!blackCanMove) {
            return "Red wins!";
        } else if (!redCanMove) {
            return "Black wins!";
        }

        return "Game is ongoing";
    }

    // Method to capture a piece
    public boolean capturePiece(long pieceId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (pieceOpt.isPresent()) {
            PieceModel piece = pieceOpt.get();
            pieceRepository.delete(piece);
            // Additional logic to handle the removal of captured piece from the board
            return true;
        }
        return false;
    }

    private void setupPieces(BoardModel board) {
        for (TileModel tile : board.getTiles()) {
            if (shouldPlacePiece(tile)) {
                PieceModel piece = new PieceModel();
                piece.setColor(determineColor(tile));
                piece.setBoardRow(tile.getBoardRow());
                piece.setBoardColumn(tile.getBoardColumn());
                piece.setKing(false);
                pieceRepository.save(piece);

                tile.setIsOccupied(true);
                tileRepository.save(tile);
            }
        }
    }

    private boolean shouldPlacePiece(TileModel tile) {
        int row = tile.getBoardRow();
        return (row < 3 || row >= 5) && (row % 2 != tile.getBoardColumn() % 2);
    }

    private String determineColor(TileModel tile) {
        return tile.getBoardRow() < 3 ? "black" : "red";
    }

}