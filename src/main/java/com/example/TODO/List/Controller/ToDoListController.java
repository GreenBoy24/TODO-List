package com.example.TODO.List.Controller;

import com.example.TODO.List.Entity.ToDoList;
import com.example.TODO.List.Functionality.ToDoListService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * java-doc
 */
@Controller
@ComponentScan(basePackages = {"com.example.TODO.List.*"})
@RequestMapping("/List")
public class ToDoListController {

    private final ToDoListService toDoListService;
    // TODO: Где инжектится ToDoListService? @Autowired нет, принудительного вызова конструткора нет,
    //  указаний в XML тоже нет, присвеоение через setter нет
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    // TODO: контроллеры ничего не должны знать про судности модели. для передачи данных туда-сюда есть "петтерн" DTO
    // в гуглде можно набрать DTO зачем нужны

    @PostMapping("/add")
    public ResponseEntity<ToDoList> add(@RequestBody ToDoList toDoList){
        if (toDoList.getName() == null || toDoList.getName().trim().length() == 0) {
            return new ResponseEntity("this list not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(toDoListService.add(toDoList));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ToDoList toDoList){

        if (toDoList.getName() == null || toDoList.getName().trim().length() == 0) {
            return new ResponseEntity("this list not found", HttpStatus.NOT_ACCEPTABLE);
        }

        toDoListService.update(toDoList);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ToDoList> findById(@PathVariable UUID id) {

        ToDoList toDoList = null;
        try{
            toDoList = toDoListService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return  ResponseEntity.ok(toDoList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {

        try {
            toDoListService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    // TODO: как понять какие списки у нас есть вообще? не знаем каеие списки есть - не имеем точек входа для просмотра дел

}
