package org.wcci.checkers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wcci.checkers.models.PieceModel;
import org.wcci.checkers.models.TileModel;
import org.wcci.checkers.models.BoardModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface PieceRepository extends CrudRepository<PieceModel, Long> {
    
    // Method to find a piece by its tile
    Optional<PieceModel> findPieceByTile(TileModel tile);

      // Method to find pieces by color
      List<PieceModel> findByColor(String color);

      List<PieceModel> findByBoard(BoardModel board);


    // Method to count pieces by color
    long countByColor(String color);

    // Method to check if there are pieces of a certain color that can move
    boolean existsByColorAndCanMove(String color, boolean canMove);
}