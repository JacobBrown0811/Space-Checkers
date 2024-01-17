package org.wcci.checkers.models;

import jakarta.persistence.*;

@Entity
public class TileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardModel board;

    private boolean isOccupied;
    private int boardColumn;
    private int boardRow;
    private String color;
    private boolean isPlayable;

    public TileModel() {
    }

    public TileModel(boolean isOccupied, int boardColumn, int boardRow, String color, boolean isPlayable) {
        this.isOccupied = isOccupied;
        this.boardColumn = boardColumn;
        this.boardRow = boardRow;
        this.color = color;
        this.isPlayable = isPlayable;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getBoardColumn() {
        return boardColumn;
    }

    public void setBoardColumn(int boardColumn) {
        this.boardColumn = boardColumn;
    }

    public int getBoardRow() {
        return boardRow;
    }

    public void setBoardRow(int boardRow) {
        this.boardRow = boardRow;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getIsPlayable() {
        return isPlayable;
    }

    public void setIsPlayable(boolean isPlayable) {
        this.isPlayable = isPlayable;
    }

    public BoardModel getBoard() {
        return board;
    }

    public void setBoard(BoardModel board) {
        this.board = board;
    }

}