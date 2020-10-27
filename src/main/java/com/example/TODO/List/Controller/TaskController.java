package com.example.todo.list.controller;

import com.example.todo.list.entity.Task;
import com.example.todo.list.exception.IncorrectNameException;
import com.example.todo.list.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@ComponentScan(basePackages = {"com.example.todo.list.*"})
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) throws IncorrectNameException {
        if (task.getName() == null || task.getTitle().trim().length() == 0) {
            throw new IncorrectNameException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {

        final boolean updated = taskService.update(task);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {

        final boolean deleted = taskService.deleteById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable UUID id) {

        Task task = taskService.findById(id);
        return task != null
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
