package com.example.TODO.List.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

public class ToDoList {
    private UUID id;
    private String name;
    private Date createdDate;
    private Date changedDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID getId() {
        return id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "createdDate")
    public Date  getCreatedDate() {
        return createdDate;
    }

    @Basic
    @Column(name = "changedDate")
    public Date  getChangedDate() {
        return changedDate;
    }
}
