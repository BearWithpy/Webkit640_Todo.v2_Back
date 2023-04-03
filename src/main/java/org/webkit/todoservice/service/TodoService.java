package org.webkit.todoservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webkit.todoservice.domain.TodoEntity;
import org.webkit.todoservice.persistence.TodoRepository;

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

        if(entity.getId() == null){
            log.warn("Unknown USER");
            throw new RuntimeException("Unknown USER");
        }
    }

    public Optional<TodoEntity> create(final TodoEntity entity){
        // validate(entity);

        repository.save(entity);
        return repository.findById(entity.getId());
    }

    public String testService(){
        TodoEntity entity = TodoEntity.builder().userId("USER 01").title("Item 01").build();
        repository.save(entity);

        return repository.searchByUserId(entity.getId()).get(0).getUserId();

    }


}
