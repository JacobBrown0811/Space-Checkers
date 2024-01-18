package org.wcci.checkers.Node;

import org.wcci.checkers.models.BoardModel;
import org.wcci.checkers.models.MoveModel;
import java.util.List;

public class Node {
    private final BoardModel board;
    private final MoveModel move;
    private final Node parent; // Reference to the parent node
    private List<Node> children; // List of children nodes
    private final int depth; // The depth of this node in the game tree
    private Integer evaluationScore; // Cache the evaluation score

    public Node(BoardModel board, MoveModel move, Node parent, int depth) {
        this.board = board;
        this.move = move;
        this.parent = parent;
        this.depth = depth;
        this.evaluationScore = null; // Initially, there's no evaluation score
    }

    public BoardModel getBoard() {
        return board;
    }

    public MoveModel getMove() {
        return move;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    public Integer getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Integer evaluationScore) {
        this.evaluationScore = evaluationScore;
    }
}