package org.wcci.checkers.service;

import org.springframework.stereotype.Service;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.models.TileModel;
import org.wcci.checkers.repositories.PieceRepository;
import org.wcci.checkers.repositories.TileRepository;
import java.util.Optional;

@Service
public class PieceService {

    private static final int BOARD_SIZE = 8; 

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
                piece.setTile(newTile);
                // check for king
                if ((piece.getColor().equals("blue") && piece.getBoardRow() == 0) || (piece.getColor().equals("red") && piece.getBoardRow() == 7)) {
                    piece.setKing(true);
                }
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

    public boolean canMove(long pieceId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (!pieceOpt.isPresent()) {
            return false;
        }

        PieceModel piece = pieceOpt.get();
        int currentRow = piece.getBoardRow();
        int currentColumn = piece.getBoardColumn();
        boolean isKing = piece.isKing();
        int[] moveRowOffsets = isKing ? new int[]{-1, 1} : new int[]{-1}; // Assuming regular pieces move upwards
        int[] moveColOffsets = new int[]{-1, 1}; // Left and right

        for (int rowOffset : moveRowOffsets) {
            for (int colOffset : moveColOffsets) {
                int newRow = currentRow + rowOffset;
                int newCol = currentColumn + colOffset;
                if (isValidTile(newRow, newCol) && !isOccupied(newRow, newCol)) {
                    return true; // Found a valid move
                }
            }
        }

        return false; // No valid moves found
    }

    public boolean canCapture(long pieceId) {
        Optional<PieceModel> pieceOpt = pieceRepository.findById(pieceId);
        if (!pieceOpt.isPresent()) {
            return false;
        }

        PieceModel piece = pieceOpt.get();
        int currentRow = piece.getBoardRow();
        int currentColumn = piece.getBoardColumn();
        boolean isKing = piece.isKing();
        int[] captureRowOffsets = isKing ? new int[]{-2, 2} : new int[]{-2}; // Assuming regular pieces move upwards
        int[] captureColOffsets = new int[]{-2, 2}; // Left and right

        for (int rowOffset : captureRowOffsets) {
            for (int colOffset : captureColOffsets) {
                int midRow = currentRow + rowOffset / 2;
                int midCol = currentColumn + colOffset / 2;
                int newRow = currentRow + rowOffset;
                int newCol = currentColumn + colOffset;
                if (isValidTile(newRow, newCol) && isOpponentPiece(midRow, midCol, piece.getColor()) && !isOccupied(newRow, newCol)) {
                    return true; // Found a valid capture
                }
            }
        }

        return false; // No valid captures found
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
    private boolean isValidTile(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }
        Optional<TileModel> tileOpt = tileRepository.findByBoardRowAndBoardColumn(row, col);
        return tileOpt.map(TileModel::getIsPlayable).orElse(false);
    }

    private boolean isOccupied(int row, int col) {
        Optional<TileModel> tileOpt = tileRepository.findByBoardRowAndBoardColumn(row, col);
        return tileOpt.map(TileModel::getIsOccupied).orElse(false);
    }

    private boolean isOpponentPiece(int row, int col, String pieceColor) {
        Optional<TileModel> tileOpt = tileRepository.findByBoardRowAndBoardColumn(row, col);
        if (tileOpt.isPresent() && tileOpt.get().getIsOccupied()) {
            // Assuming you have a method in your repository to find the piece by tile
            Optional<PieceModel> pieceOpt = pieceRepository.findPieceByTile(tileOpt.get());
            if (pieceOpt.isPresent()) {
                PieceModel piece = pieceOpt.get();
                return !piece.getColor().equalsIgnoreCase(pieceColor);
            }
        }
        return false;
    }
}


