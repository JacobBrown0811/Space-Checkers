package org.wcci.checkers.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String color;// Represents the color of the player's pieces
    private boolean isTurn = false; 

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PieceModel> pieces = new ArrayList<>(); // The pieces that belong to the player

    public PlayerModel() {
    }

    public PlayerModel(String color) {
        this.color = color;
    }

    // Add a piece to the player's set of pieces
    public void drawPiece(PieceModel piece) {
        pieces.add(piece);
        piece.setPlayer(this); // Set the back reference to the player
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<PieceModel> getPieces() {
        return pieces;
    }

    public void setPieces(List<PieceModel> pieces) {
        this.pieces = pieces;
    }
}
