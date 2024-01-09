package org.wcci.checkers.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BoardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TileModel> tiles = new ArrayList<>();

    public BoardModel() {
        drawBoard();//may need to move to service
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

 @JsonIgnore
    public List<TileModel> getTiles() {
        return tiles;
    }

    public void setTiles(List<TileModel> tiles) {
        this.tiles = tiles;
    }
}
