package org.wcci.checkers.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BoardModel {
    

    @Id
    private long id = 1;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TileModel> tiles = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PieceModel> pieces = new ArrayList<>();

    public BoardModel() {
        // drawBoard();//may need to move to service
    }

    public void drawTiles() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                TileModel tile = new TileModel();
                tile.setBoardRow(row);
                tile.setBoardColumn(col);
                tile.setColor((row % 2 != col % 2) ? "white" : "black");
                tile.setBoard(this); // Link each tile to the board
                tiles.add(tile);
            }
        }
    }
    public void drawPieces() {
        for (TileModel tile : this.getTiles()) {
            int row = tile.getBoardRow();
            if ((row < 3 || row >= 5) && tile.getColor().equals("black")) {
                PieceModel piece = new PieceModel();
                piece.setColor(row < 3 ? "red" : "blue");
                piece.setBoardRow(row);
                piece.setBoardColumn(tile.getBoardColumn());
                piece.setKing(false);
                piece.setBoard(this);
                tile.setIsOccupied(true);
                piece.setTile(tile);
                pieces.add(piece);
            }
        }
    }

    // Getters and setters
    public long getId() {
        return id;
    }

 public void setId(long id) {
        this.id = id;
    }

@JsonIgnore
    public List<TileModel> getTiles() {
        return tiles;
    }

    public void setTiles(List<TileModel> tiles) {
        this.tiles = tiles;
    }



private TileModel getTileAt(int row, int col) {
    for (TileModel tile : tiles) {
        if (tile.getBoardRow() == row && tile.getBoardColumn() == col) {
            return tile;
        }
    }
    return null; // TODO are we using this? Return null if the tile is not found at the specified row and column
}
@JsonIgnore
public List<PieceModel> getPieces() {
    return pieces;
}

public void setPieces(List<PieceModel> pieces) {
    this.pieces = pieces;
}

public PieceModel getPieceAt(int startX, int startY) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPieceAt'");
}

}
