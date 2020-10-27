package com.example.todo.list.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "ToDoList")
public class ToDoList {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Basic
    @NotNull
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "createdDate")
    private Date createdDate;

    @Basic
    @Column(name = "changedDate")
    private Date changedDate;

}
