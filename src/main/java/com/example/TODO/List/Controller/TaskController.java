package com.example.todo.list.controller;

import com.example.todo.list.dto.TaskDto;
import com.example.todo.list.entity.Task;
import com.example.todo.list.exception.IncorrectNameException;
import com.example.todo.list.mapper.TaskMapper;
import com.example.todo.list.search.TaskSearch;
import com.example.todo.list.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Контроллер обрабатывающий запросы  связанные с задачами
 * @autor Пётр
 */
@RestController
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.example.todo.list.*"})
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping(value="")
    public String hello() {
        return "Task";
    }

    /**
     * Метод который добавляет задачу
     */
    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody TaskDto taskDto) throws IncorrectNameException {
        Task task = taskMapper.toEntity(taskDto);
        task= taskService.add(task);
        taskDto = taskMapper.toDTO(task);

        if (taskDto.getName() == null || taskDto.getName().trim().length() == 0) {
            throw new IncorrectNameException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Метод который обнавляет задачу
     */
    @PutMapping("/update")
    public ResponseEntity<TaskDto> update(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        final boolean updated = taskService.update(task);

        return updated
                ? new ResponseEntity<>(taskDto,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    /**
     * Метод который удаляет задачу по идентификатору
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {

        final boolean deleted = taskService.deleteById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Метод который показывает все задачи
     */
    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    /**
     * Метод который показывает задачу по идентификатору
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable UUID id) {
        Task task = taskService.findById(id);
        TaskDto taskDto = taskMapper.toDTO(task);
        return taskDto != null
                ? new ResponseEntity<>(taskDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод поиска задач
     */
    @PostMapping("/search")
    public ResponseEntity<Page> search(@RequestBody TaskSearch taskSearch, @RequestBody Task task) {

        Sort.Direction direction = taskSearch.getSortDirection() == null || taskSearch.getSortDirection().trim().length() == 0 || taskSearch.getSortDirection().trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, taskSearch.getSortDirection());

        PageRequest pageRequest = PageRequest.of(taskSearch.getPageNumber(), taskSearch.getPageSize(), sort);

        Page result = taskService.findByParams(task.getId(), task.getName(), task.getCompleted(), task.getCreatedDate(), task.getChangedDate(), task.getPriority(), pageRequest);

        return new ResponseEntity<Page>(result, HttpStatus.OK);

    }

    /**
     * Метод который обнавляет статус задачи
     */
    @PutMapping("/state/{id}")
    public ResponseEntity<TaskDto> changeDoneStat(@PathVariable UUID id) {

        Task task = taskService.isCompleat(id);
        TaskDto taskDto = taskMapper.toDTO(task);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }
}
