package org.webkit.todoservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webkit.todoservice.domain.TodoEntity;
import org.webkit.todoservice.dto.ResponseDTO;
import org.webkit.todoservice.dto.TodoDTO;
import org.webkit.todoservice.service.TodoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@Slf4j
public class TodoController {

    @Autowired
    private TodoService service;

    String tempUserId = "temp-userId";

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try {
            log.info("Log: createTodo Start!");

            TodoEntity entity = TodoDTO.toEntity(dto);
            log.info("Log: DTO to Entity");

            entity.setUserId(tempUserId);
            System.out.println(entity.getUserId());
            log.info("Log: set userID!");

            Optional<TodoEntity> entities = service.create(entity);
            log.info("Log: service.create OK!");

            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            log.info("Log: Entities to DTOs");

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            log.info("Log: response OK!");

            return ResponseEntity.ok().body(response);



        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping
    public ResponseEntity<?> retrieveTodo(){

        List<TodoEntity> entities = service.retrieve(tempUserId);
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);

    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
        try{
            TodoEntity entity = TodoDTO.toEntity(dto);

            entity.setUserId(tempUserId);

            Optional<TodoEntity> entities = service.updateTodo(entity);
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);

        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }





}
