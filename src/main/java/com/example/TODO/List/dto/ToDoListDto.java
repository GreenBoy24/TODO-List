package com.example.todo.list.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ToDoListDto {

    private UUID id;
    private String name;
    private Date createdDate;
    private Date changedDate;

    public ToDoListDto(UUID id, String name, Date createdDate, Date changedDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.changedDate = changedDate;
    }
}
