package org.webkit.todoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webkit.todoservice.dto.ResponseDTO;
import org.webkit.todoservice.dto.TestRequestBodyDTO;
import org.webkit.todoservice.service.TodoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TodoService service;

//    @GetMapping
//    public String testController() {
//        return "Hello World!";
//    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
        return "Hello World! ID: " + id;
    }

    @GetMapping("/Param")
    public String testControllerRequestParam(@RequestParam(required = false) int id) {
        // http://localhost:8080/test/Param?id=123
        return "Hello World! ID param: " + id;
    }

    @GetMapping("/testBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO dto) {
        return "Hello World! ID param: " + dto.getId() + "Message: " + dto.getMessage();
    }

    @GetMapping("/testResponseEntityBad")
    public ResponseEntity<?> testControllerResponseEntityBad() {
        List<String> list = new ArrayList<String>();
        list.add("You get 4XX");
        list.add("See ya!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping()
    public ResponseEntity<?> testTodoService() {

        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

        return ResponseEntity.ok().body(response);
    }
}
