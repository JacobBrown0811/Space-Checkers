package org.wcci.checkers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.checkers.models.BoardModel;

public interface BoardRepository extends CrudRepository<BoardModel, Long> {
    
}
