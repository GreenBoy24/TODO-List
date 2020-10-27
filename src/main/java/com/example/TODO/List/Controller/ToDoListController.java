package com.example.todo.list.controller;

import com.example.todo.list.entity.ToDoList;
import com.example.todo.list.exception.IncorrectNameException;
import com.example.todo.list.services.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@ComponentScan(basePackages = {"com.example.todo.list.*"})
@RequestMapping("/list")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @PostMapping("/add")
    public ResponseEntity<ToDoList> add(@RequestBody ToDoList toDoList) throws IncorrectNameException {
        if (toDoList.getName() == null || toDoList.getName().trim().length() == 0) {
            throw new IncorrectNameException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ToDoList toDoList){

        final boolean updated = toDoListService.update(toDoList);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoList> findById(@PathVariable UUID id) {

        ToDoList toDoList = toDoListService.findById(id);
        return toDoList != null
                ? new ResponseEntity<>(toDoList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {

        final boolean deleted = toDoListService.deleteById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
