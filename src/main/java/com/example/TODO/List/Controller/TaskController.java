package com.example.TODO.List.Controller;

import com.example.TODO.List.Entity.Task;
import com.example.TODO.List.Functionality.TaskService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@ComponentScan(basePackages = {"com.example.TODO.List.*"})
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    // TODO: Где инжектится TaskService? @Autowired нет, принудительного вызова конструткора нет,
    //  указаний в XML тоже нет, присвеоение через setter нет
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // TODO: контроллеры ничего не должны знать про судности модели. для передачи данных туда-сюда есть "петтерн" DTO
    // в гуглде можно набрать DTO зачем нужны

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("this title not exist", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(taskService.add(task));
    }


    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("this title not found", HttpStatus.NOT_ACCEPTABLE);
        }
        taskService.update(task);
        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {

        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {// TODO: выбирать дела мы должны из списка, значит номер списка нужен,
        // зачем нам все дела без списков?
        // по условиям задачи у нас есть фильтрация и сортировка,
        // + отсечение лишнего, и сразу кейс: дел в списке у нас 120, как посмотерть 20 дел после 100?
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/id/{id}") // TODO: зачем тут в пути id, можно обойтись без него
    public ResponseEntity<Task> findById(@PathVariable UUID id) {

        Task task = null;

        try {
            task = taskService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(task);
    }
}
