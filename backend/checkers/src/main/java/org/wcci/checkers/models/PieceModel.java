package org.wcci.checkers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class PieceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardModel board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerModel player;

    @OneToOne
    @JoinColumn(name = "tile_id")
    private TileModel tile;

    private String color;
    private boolean king = false;
    private int boardColumn;
    private int boardRow;
    private boolean canMove;
    private boolean canCapture;

    public PieceModel() {
    }

    public PieceModel(String color, boolean king, int boardColumn, int boardRow, boolean canMove, boolean canCapture) {
        this.color = color;
        this.king = king;
        this.boardColumn = boardColumn;
        this.boardRow = boardRow;
        this.canMove = canMove;
        this.canCapture = canCapture;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isKing() {
        return king;
    }

    public void setKing(boolean king) {
        this.king = king;
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

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean canCapture() {
        return canCapture;
    }

    public void setCanCapture(boolean canCapture) {
        this.canCapture = canCapture;
    }

    public void setPlayer(PlayerModel playerModel) {
    }

    public BoardModel getBoard() {
        return board;
    }

    public void setBoard(BoardModel board) {
        this.board = board;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public boolean isCanCapture() {
        return canCapture;
    }

    public TileModel getTile() {
        return tile;
    }

    public void setTile(TileModel tile) {
        this.tile = tile;
    }

}