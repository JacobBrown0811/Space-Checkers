package org.wcci.checkers.models;

public class PieceModel {
    String color;
    Boolean king = false;
    int column;
    int row;
    Boolean canMove;
    Boolean canCapture;
    
    public PieceModel() {
    }

    public PieceModel(String color, Boolean king, int column, int row, Boolean canMove, Boolean canCapture) {
        this.color = color;
        this.king = king;
        this.column = column;
        this.row = row;
        this.canMove = canMove;
        this.canCapture = canCapture;
    }

    public String getColor() {
        return color;
    }

    public Boolean getKing() {
        return king;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Boolean getCanMove() {
        return canMove;
    }

    public Boolean getCanCapture() {
        return canCapture;
    }

    public void setKing(Boolean king) {
        this.king = king;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCanMove(Boolean canMove) {
        this.canMove = canMove;
    }

    public void setCanCapture(Boolean canCapture) {
        this.canCapture = canCapture;
    }
    
    
}