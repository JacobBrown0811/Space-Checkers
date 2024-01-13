package org.wcci.checkers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.checkers.models.PieceModel;

public interface PieceRepository extends CrudRepository<PieceModel, Long> {
    
    // TODO Method to count pieces by color
    long countByColor(String color);

    // TODO Method to check if there are pieces of a certain color that can move
    boolean existsByColorAndCanMove(String color, boolean canMove);
}