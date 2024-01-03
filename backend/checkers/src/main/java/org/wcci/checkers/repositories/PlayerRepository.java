package org.wcci.checkers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.checkers.models.PlayerModel;

public interface PlayerRepository extends CrudRepository<PlayerModel, Long>{
    
}
