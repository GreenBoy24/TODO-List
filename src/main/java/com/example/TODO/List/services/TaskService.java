package com.example.todo.list.services;

import com.example.todo.list.entity.Task;
import com.example.todo.list.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для класса Task
 * @author Пётр
 */
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

    public Task isCompleat(UUID id){
        Task task = findById(id);
        task.setCompleated(!task.getCompleated());
        repository.save(task);
        return task;
    }

    public Page<Task> findByParams(UUID id, String name, boolean completed, Date date, Date changedDate, int priority, Pageable pageable) {
        return repository.findByParams(id, name, completed, date, changedDate, priority, pageable);
    }
}
