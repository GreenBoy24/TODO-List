package com.example.todo.list.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Basic
    @NotNull
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "completed")
    private boolean completed;

    @Basic
    @Column(name = "createdDate")
    private Date createdDate;

    @Basic
    @Column(name = "changedDate")
    private Date changedDate;

    @Basic
    @Column(name = "priority")
    private Integer priority;
    
}
