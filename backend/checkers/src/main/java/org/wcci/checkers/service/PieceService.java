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

    /**
     * set piece location on move.
     * TODO Needs update to use tile ID instead of row, column
     * 
     * @param pieceId
     * @param newRow
     * @param newColumn
     * @return
     */
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

    /**
     * delete piece on capture
     * 
     * @param pieceId
     * @return
     */
    public boolean capturePiece(long pieceId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (pieceOpt.isPresent()) {
            pieceRepository.delete(pieceOpt.get());
            return true;
        }
        return false;
    }

}
