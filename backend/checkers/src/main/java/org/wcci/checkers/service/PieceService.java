package org.wcci.checkers.service;

import org.springframework.stereotype.Service;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.models.TileModel;
import org.wcci.checkers.repositories.PieceRepository;
import org.wcci.checkers.repositories.TileRepository;

import java.util.Optional;

@Service
public class PieceService {

    private final PieceRepository pieceRepository;
    private final TileRepository tileRepository;

    public PieceService(PieceRepository pieceRepository, TileRepository tileRepository) {
        this.pieceRepository = pieceRepository;
        this.tileRepository = tileRepository;
    }

    public boolean movePiece(long pieceId, int newRow, int newColumn) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (pieceOpt.isPresent()) {
            PieceModel piece = pieceOpt.get();
            piece.setBoardRow(newRow);
            piece.setBoardColumn(newColumn);
            pieceRepository.save(piece);
            return true;
        }
        return false;
    }

    public boolean capturePiece(long pieceId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (pieceOpt.isPresent()) {
            pieceRepository.delete(pieceOpt.get());
            return true;
        }
        return false;
    }

    public void setupPieces(BoardModel board) {
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