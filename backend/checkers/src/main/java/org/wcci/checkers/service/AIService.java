package org.wcci.checkers.service;

import org.springframework.stereotype.Service;
import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.models.MoveModel;
import org.wcci.checkers.models.PieceModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIService {

    private static final int MAX_DEPTH = 5; // You can adjust this value
    private static final int KING_VALUE = 10;

    public MoveModel calculateBestMove(BoardModel board) {
        Node root = new Node(board, null);
        return minimax(root, true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0).move;
    }

    private Result minimax(Node node, boolean isMaximizingPlayer, int alpha, int beta, int depth) {
        if (depth >= MAX_DEPTH || isTerminal(node)) {
            return new Result(evaluationFunction(node.board), null);
        }

        MoveModel bestMove = null;
        int bestScore = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (MoveModel move : getPossibleMoves(node.board, isMaximizingPlayer)) {
            Node childNode = generateChildNode(node, move);
            Result result = minimax(childNode, !isMaximizingPlayer, alpha, beta, depth + 1);

            if (isMaximizingPlayer) {
                if (result.score > bestScore) {
                    bestScore = result.score;
                    bestMove = move;
                }
                alpha = Math.max(alpha, bestScore);
            } else {
                if (result.score < bestScore) {
                    bestScore = result.score;
                    bestMove = move;
                }
                beta = Math.min(beta, bestScore);
            }

            if (beta <= alpha) {
                break;
            }
        }

        return new Result(bestScore, bestMove);
    }

  private int evaluationFunction(BoardModel board) {
    int score = 0;
    int aiPiecesCount = 0;
    int playerPiecesCount = 0;
    int aiKingsCount = 0;
    int playerKingsCount = 0;

    // Iterate over the pieces and calculate scores based on piece type and position
    for (PieceModel piece : board.getPieces()) {
        if (piece.getColor().equals("red")) { // Replace "AI_COLOR" with the actual AI color
            aiPiecesCount++;
            if (piece.isKing()) {
                aiKingsCount++;
            }
            // Add more points for pieces closer to being kinged, etc.
        } else {
            playerPiecesCount++;
            if (piece.isKing()) {
                playerKingsCount++;
            }
            // Similar scoring for player's pieces
        }
    }

    score += aiPiecesCount - playerPiecesCount;
    score += (aiKingsCount * KING_VALUE) - (playerKingsCount * KING_VALUE); // KING_VALUE is the additional value for king pieces

    return score;
}

private Node generateChildNode(Node node, MoveModel move) {
    BoardModel newBoard = applyMove(node.board, move);
    return new Node(newBoard, move);
}

private BoardModel applyMove(BoardModel board, MoveModel move) {
    // Fetch the piece from the board
    PieceModel piece = board.getPieceAt(move.getStartX(), move.getStartY());

    if (piece != null) {
        // Logic to apply the move
        // This may involve updating the piece's position, capturing pieces, etc.

        // For example, move the piece to the new position
        piece.setBoardRow(move.getEndX());
        piece.setBoardColumn(move.getEndY());

        // If capturing a piece, handle that logic
        // ...

        // Return the updated board
        return board;
    } else {
        // Handle the case where no piece is found at the start position
        // This might involve throwing an exception or handling the error gracefully
        return board;
    }
}



private List<MoveModel> getPossibleMoves(BoardModel board, boolean isMaximizingPlayer) {
    List<MoveModel> possibleMoves = new ArrayList<>();
    String playerColor = isMaximizingPlayer ? "red" : "blue"; // Adjust these as per your color naming

    for (PieceModel piece : board.getPieces()) {
        if (piece.getColor().equals(playerColor)) {
            // Check all possible moves for this piece
            // This includes regular moves and captures

            // Regular moves
            // For each direction that the piece can move, check if the move is valid
            // If valid, add to possibleMoves
            
            // Captures
            // Check if the piece can capture any opponent's pieces
            // If yes, add those capture moves to possibleMoves
        }
    }

    return possibleMoves;
}

    private boolean isTerminal(Node node) {
        // TODO: Implement logic to determine if the game is over.
        // This could be based on checking if a player has no pieces left or no legal moves.
        return false;
    }

    private static class Node {
        private final BoardModel board;
        private final MoveModel move;

        private Node(BoardModel board, MoveModel move) {
            this.board = board;
            this.move = move;
        }
    }

    private static class Result {
        private final int score;
        private final MoveModel move;

        private Result(int score, MoveModel move) {
            this.score = score;
            this.move = move;
        }
    }
}
