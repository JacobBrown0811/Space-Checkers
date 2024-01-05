package org.wcci.checkers.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.wcci.checkers.service.Piece;

@Entity
public class BoardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TileModel> tiles = new ArrayList<>();

    public BoardModel() {
        drawBoard();
    }

    public void drawBoard() {
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

    // Getters and setters
    public long getId() {
        return id;
    }

    public List<TileModel> getTiles() {
        return tiles;
    }

    public void setTiles(List<TileModel> tiles) {
        this.tiles = tiles;
    }

    public void placePiece(Piece piece, int row, int col) {
    TileModel tile = getTileAt(row, col);
    if (tile != null) {
        tile.setPiece(piece); // Set the piece on the tile
    }
}

private TileModel getTileAt(int row, int col) {
    for (TileModel tile : tiles) {
        if (tile.getBoardRow() == row && tile.getBoardColumn() == col) {
            return tile;
        }
    }
    return null; // Return null if the tile is not found at the specified row and column
}
}
