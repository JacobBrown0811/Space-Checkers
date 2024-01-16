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
    public boolean movePiece(long pieceId, long newTileId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        Optional<TileModel> tileOpt = tileRepository.findById(newTileId);
        
        if (pieceOpt.isPresent()) {
            try {
            PieceModel piece = pieceOpt.get();
            TileModel newTile = tileOpt.get();
            TileModel oldTile = piece.getTile();
            oldTile.setIsOccupied(false);
            piece.setBoardRow(newTile.getBoardRow());
            piece.setBoardColumn(newTile.getBoardColumn());
            newTile.setIsOccupied(true);
            tileRepository.save(oldTile);
            tileRepository.save(newTile);
            pieceRepository.save(piece);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No can do bud: " + e.getMessage());
            return false;
        }
        }
        return false;
    }

    public boolean canMove(long pieceId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (pieceOpt.isPresent()) {
            try {
                PieceModel piece = pieceOpt.get();
                int pRow = piece.getBoardRow();
                int pCol = piece.getBoardColumn();
                long tileId = piece.getTile().getId();
                    long trTile = tileId - 7;
                    long tlTile = tileId - 9;
                if (piece.isKing()) {
                    long brTile = tileId + 9;
                    long blTile = tileId + 7;
                } else {                  
                    if ()
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
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
