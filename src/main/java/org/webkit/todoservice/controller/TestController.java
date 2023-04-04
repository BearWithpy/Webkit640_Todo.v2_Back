package org.webkit.todoservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.webkit.todoservice.domain.TodoEntity;
import org.webkit.todoservice.dto.ResponseDTO;
import org.webkit.todoservice.dto.TodoDTO;
import org.webkit.todoservice.service.TodoService;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@Slf4j
public class TestController {

    @Autowired
    private TodoService service;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try {
            log.info("Log: createTodo Start!");

            TodoEntity entity = TodoDTO.toEntity(dto);
            log.info("Log: DTO to Entity");

            entity.setUserId("temp-userId");

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


}
