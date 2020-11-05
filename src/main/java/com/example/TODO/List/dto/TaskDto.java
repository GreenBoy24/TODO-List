package com.example.todo.list.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TaskDto {
    private UUID id;
    private String name;
    private Date createdDate;
    private Date changedDate;
    private boolean completed;
    private int priority;

    public TaskDto(UUID id, String name, Date createdDate, Date changedDate, boolean completed, int priority) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.changedDate = changedDate;
        this.completed = completed;
        this.priority = priority;
    }
}
