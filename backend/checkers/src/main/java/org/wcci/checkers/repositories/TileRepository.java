package org.wcci.checkers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wcci.checkers.models.TileModel;
import java.util.Optional;

@Repository
public interface TileRepository extends CrudRepository<TileModel, Long> {
    
    // Method to find a tile by its row and column
    Optional<TileModel> findByBoardRowAndBoardColumn(int boardRow, int boardColumn);
}