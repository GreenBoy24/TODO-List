package com.example.TODO.List.Functionality;

import com.example.TODO.List.Entity.Task;
import com.example.TODO.List.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task add(Task task) {
        return repository.save(task);
    }

    public Task update(Task task){
        return repository.save(task);
    }

    public void deleteById(UUID id){
        repository.deleteById(id);
    }

    public Task findById(UUID id){
        return repository.findById(id).get();
    }
}
