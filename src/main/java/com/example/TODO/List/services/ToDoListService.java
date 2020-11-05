package com.example.todo.list.services;

import com.example.todo.list.entity.ToDoList;
import com.example.todo.list.mapper.ToDoListMapper;
import com.example.todo.list.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для класса TodoList
 * @author Пётр
 */
@Service
@RequiredArgsConstructor
public class ToDoListService implements ServiceForToDoList {

    private final ToDoListRepository repository;
    //private final ToDoListMapper toDoListMapper;

    @Override
    public ToDoList add(ToDoList toDoList) {
        return repository.save(toDoList);
    }

    @Override
    public boolean update(ToDoList toDoList) {
        if(repository.existsById(toDoList.getId())){
            repository.save(toDoList);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ToDoList findById(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public List<ToDoList> findAll() {
        return repository.findAll();
    }

    public Page findByTitle(PageRequest paging ){
        return repository.findByTitle(paging);
    }
}
