package org.wcci.checkers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.checkers.models.PieceModel;

public interface PieceRepository extends CrudRepository<PieceModel, Long>{
    
}
