package org.wcci.checkers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.checkers.models.TileModel;

public interface TileRepository extends CrudRepository<TileModel, Long>{
    
}
