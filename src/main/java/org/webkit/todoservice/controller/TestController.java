package org.webkit.todoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webkit.todoservice.dto.ResponseDTO;
import org.webkit.todoservice.service.TodoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TodoService service;

}
