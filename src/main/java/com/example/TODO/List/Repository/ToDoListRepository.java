package com.example.todo.list.repository;

import com.example.todo.list.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ToDoListRepository extends JpaRepository<ToDoList, UUID> {
}
