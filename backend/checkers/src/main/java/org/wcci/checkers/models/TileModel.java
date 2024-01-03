package org.wcci.checkers.models;

import jakarta.persistence.*;

@Entity
public class TileModel {

    @ManyToOne
    BoardModel board;

    @Id
    @GeneratedValue
    long id;
    
    Boolean isOccupied;
    int column;
    int row;
    String color;
    Boolean isPlayable;
    
    public TileModel() {
    }

    public TileModel(Boolean isOccupied, int column, int row, String color, Boolean isPlayable) {
        this.isOccupied = isOccupied;
        this.column = column;
        this.row = row;
        this.color = color;
        this.isPlayable = isPlayable;
    }

    public Boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(Boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String getColor() {
        return color;
    }

    public Boolean getIsPlayable() {
        return isPlayable;
    }

    
    
}