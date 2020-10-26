package com.example.TODO.List.Functionality;

import com.example.TODO.List.Entity.ToDoList;
import com.example.TODO.List.Repository.ToDoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ToDoListService {

    private final ToDoListRepository repository;
    // TODO: Где инжектится ToDoListRepository? @Autowired нет, принудительного вызова конструткора нет,
    //  указаний в XML тоже нет, присвеоение через setter нет
    public ToDoListService(ToDoListRepository repository) {
        this.repository = repository;
    }

    public List<ToDoList> findAll() {
        return repository.findAll();
    }

    public ToDoList add(ToDoList toDoList) {
        return repository.save(toDoList);
    }

    public ToDoList update(ToDoList toDoList){
        return repository.save(toDoList);
    }

    public void deleteById(UUID id){
        repository.deleteById(id);
    }

    public ToDoList findById(UUID id){
        return repository.findById(id).get();
    }
}
