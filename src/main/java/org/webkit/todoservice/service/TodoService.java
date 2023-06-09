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

    public void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown USER");
            throw new RuntimeException("Unknown USER");
        }
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);
        repository.save(entity);
        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);

        if (repository.existsById(entity.getId())) {
            repository.save(entity);
        } else {
            throw new RuntimeException("Unknown ID");
        }

        return repository.findByUserId(entity.getUserId());
    }

    public Optional<TodoEntity> updateTodo(final TodoEntity entity) {
        validate(entity);

        final Optional<TodoEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo ->{
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            repository.save(todo);
        });

        return repository.findById(entity.getId());
    }

    public List<TodoEntity> delete(final TodoEntity entity){
        if(repository.existsById(entity.getId())) repository.deleteById(entity.getId());
        else throw new RuntimeException("ID Doesn't EXIST!");

        return repository.findByUserId(entity.getUserId());
    }


}
