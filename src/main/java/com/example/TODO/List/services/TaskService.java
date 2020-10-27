package com.example.todo.list.services;

import com.example.todo.list.entity.Task;
import com.example.todo.list.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService implements ServiceForTask {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task add(Task task) {
        return repository.save(task);
    }

    @Override
    public boolean update(Task task) {
        if(repository.existsById(task.getId())){
            repository.save(task);
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
    public Task findById(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }
}
