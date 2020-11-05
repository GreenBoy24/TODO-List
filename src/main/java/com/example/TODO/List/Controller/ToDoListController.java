package com.example.todo.list.controller;

import com.example.todo.list.dto.ToDoListDto;
import com.example.todo.list.entity.ToDoList;
import com.example.todo.list.exception.IncorrectNameException;
import com.example.todo.list.mapper.ToDoListMapper;
import com.example.todo.list.search.ToDoListSearch;
import com.example.todo.list.services.ToDoListService;
import liquibase.pro.packaged.T;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/**
 * Контроллер обрабатывающий запросы  связанные со списками задач
 * @autor Пётр
 */
@RestController
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.example.todo.list.*"})
@RequestMapping("/list")
public class ToDoListController {

    private final ToDoListService toDoListService;
    private final ToDoListMapper toDoListMapper;

    @GetMapping(value="")
    public String hello() {
        return "ToDoList";
    }

    /**
     * Метод который добавляет список задач
     */
    @PostMapping("/add")
    public ResponseEntity<ToDoListDto> add(@RequestBody ToDoListDto toDoListDto) throws IncorrectNameException {
        ToDoList toDoList = toDoListMapper.toEntity(toDoListDto);
        toDoList = toDoListService.add(toDoList);
        toDoListDto = toDoListMapper.toDTO(toDoList);
        
        if (toDoListDto.getName() == null || toDoListDto.getName().trim().length() == 0) {
            throw new IncorrectNameException();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Метод который обнавляет список задач
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ToDoListDto toDoListDto) throws IncorrectNameException{

        ToDoList toDoList = toDoListMapper.toEntity(toDoListDto);
        final boolean updated = toDoListService.update(toDoList);

        if (toDoList.getName() == null || toDoList.getName().trim().length() == 0) {
            throw new IncorrectNameException();
        }

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Метод который показывает список задач по заданному идентификатору
     */
    @GetMapping("/{id}")
    public ResponseEntity<ToDoList> findById(@PathVariable UUID id) {

        ToDoList toDoList = toDoListService.findById(id);
        ToDoListDto toDoListDto = toDoListMapper.toDTO(toDoList);
        return toDoList != null
                ? new ResponseEntity(toDoListDto, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод удаления списка задач по идентификатору
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {

        final boolean deleted = toDoListService.deleteById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Метод поиска списка задач
     */
    @PostMapping("/search")
    public ResponseEntity<Page<ToDoList>> findAllByTitleAsc(@RequestBody ToDoListSearch toDoListSearch) {

        Sort.Direction direction = toDoListSearch.getSortDirection() == null || toDoListSearch.getSortDirection().trim().length() == 0 || toDoListSearch.getSortDirection().trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, toDoListSearch.getSortColumn());

        PageRequest pageRequest = PageRequest.of(toDoListSearch.getPageNumber(), toDoListSearch.getPageSize(),sort);

        Page result = toDoListService.findByTitle(pageRequest);


        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
