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
                System.out.println("Problem with movePiece: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    /**
     * Returns true if piece can move
     * @param pieceId
     * @return
     */
    public boolean canMove(long pieceId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (pieceOpt.isPresent()) {
            try {
                PieceModel piece = pieceOpt.get();
                long tileId = piece.getTile().getId();
                long trTile = tileId - 7;
                long tlTile = tileId - 9;
                Optional<TileModel> trOpt = tileRepository.findById(trTile);
                Optional<TileModel> tlOpt = tileRepository.findById(tlTile);
                if (piece.isKing()) {
                    long brTile = tileId + 9;
                    long blTile = tileId + 7;
                    Optional<TileModel> brOpt = tileRepository.findById(brTile);
                    Optional<TileModel> blOpt = tileRepository.findById(blTile);
                    if (blOpt.get().getIsOccupied() && brOpt.get().getIsOccupied() && trOpt.get().getIsOccupied()
                            && tlOpt.get().getIsOccupied()) {
                        return false;
                    }
                } else {

                    if (trOpt.get().getIsOccupied() && tlOpt.get().getIsOccupied()) {
                        return false;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Problem with canMove: " + e.getMessage());
            }
        }
        return true;
    }

    public boolean canCapture(long pieceId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        PieceModel piece = pieceOpt.get();
        try {
            if (!canMove(pieceId)){
                long tileId = piece.getTile().getId();
                long trTile = tileId - 14;
                long tlTile = tileId - 18;
                Optional<TileModel> trOpt = tileRepository.findById(trTile);
                Optional<TileModel> tlOpt = tileRepository.findById(tlTile);
                if (piece.isKing()) {
                    long brTile = tileId + 18;
                    long blTile = tileId + 14;
                    Optional<TileModel> brOpt = tileRepository.findById(brTile);
                    Optional<TileModel> blOpt = tileRepository.findById(blTile);
                    if (blOpt.get().getIsOccupied() && brOpt.get().getIsOccupied() && trOpt.get().getIsOccupied()
                            && tlOpt.get().getIsOccupied()) {
                        return false;
                    }
                } else {

                    if (trOpt.get().getIsOccupied() && tlOpt.get().getIsOccupied()) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Problem with canCapture: " + e.getMessage());
        }
        return true;
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
            pieceRepository.deleteById(pieceId);
            return true;
        }
        return false;
    }

}
