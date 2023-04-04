package org.webkit.todoservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webkit.todoservice.domain.TodoEntity;
import org.webkit.todoservice.persistence.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public void validate(final TodoEntity entity){
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId() == null){
            log.warn("Unknown USER");
            throw new RuntimeException("Unknown USER");
        }
    }


    public Optional<TodoEntity> create(final TodoEntity entity){
        validate(entity);

        repository.save(entity);
        return repository.findById(entity.getId());
    }

    public List<TodoEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }


}
