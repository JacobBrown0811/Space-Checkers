package org.wcci.checkers.service;

import org.wcci.checkers.models.TileModel;

public class Piece {
    private String color;
    private boolean isKing;

    public Piece(String color) {
        this.color = color;
        this.isKing = false; // By default, pieces are not kings
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    public void setTile(TileModel tileModel) {
    }

}
