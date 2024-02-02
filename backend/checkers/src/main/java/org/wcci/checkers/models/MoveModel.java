package org.wcci.checkers.models;

public class MoveModel {
    private long boardId;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private boolean isCapture; // Indicates if the move is a capturing move
    private PieceModel capturedPiece; // Reference to the captured piece, if any

    public MoveModel(long boardId,int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.isCapture = false; // Default to false, set to true if it's a capturing move
        this.capturedPiece = null; // Default to null, set to the captured piece if it's a capturing move
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
    public boolean isCapture() {
        return isCapture;
    }

    public void setCapture(boolean capture) {
        isCapture = capture;
    }

    public PieceModel getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(PieceModel capturedPiece) {
        this.capturedPiece = capturedPiece;
    }
}
    
