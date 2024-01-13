package com.planner.travelplanner.jpa;

import com.planner.travelplanner.enums.ExceptionMessages;
import com.planner.travelplanner.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractRepository <R extends JpaRepository<EntityType,Long>, EntityType>{
    protected final Logger LOGGER = LoggerFactory.getLogger(Class.class);
    protected EntityType findEntity(R repository,Long id){
        return repository.findById(id).orElseThrow(()->{
            LOGGER.warn("Record not found");
            return new NotFoundException(ExceptionMessages.ENTITY_NOT_FOUND);
        });
    }
    protected boolean existEntityById(R repository, Long id) {
        return repository.existsById(id);
    }
}
