package com.example.TODO.List.Repository;

import com.example.TODO.List.Entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ToDoListRepository extends JpaRepository<ToDoList, UUID> {
}
